package jelena.etfbl.simulation;

import jelena.etfbl.generate.TransportDataGenerator;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * {@code Finder} is a route-finding utility class that builds a graph of departures
 * and searches for optimal routes according to given criteria
 * (cheapest, fastest, minimum number of transfers).
 *
 * <p><b>Algorithms provided:</b>
 * <ul>
 *   <li>{@link #fastestBytime(Map, String, String, int, Set, Set)} – Dijkstra by arrival time
 *       (with tie-breaker on number of transfers).</li>
 *   <li>{@link #cheapest(Map, String, String, int, Set, Set)} – Dijkstra by ticket price
 *       (primary: price, secondary: arrival time).</li>
 *   <li>{@link #minTransfers(Map, String, String, int, Set, Set)} – BFS by number of transfers
 *       (arrival time as secondary criterion within each level).</li>
 *   <li>{@link #nBestRoutes(Map, String, String, int, int, Criteria)} – Yen-style algorithm
 *       to find the N the best alternative routes based on a chosen solver.</li>
 * </ul>
 *
 * @author Jelena
 */
public class Finder {

    /**
     * Optimization criteria for route selection.
     */
    public enum Criteria{
        CHEAPEST, FASTEST, MIN_TRANSFERS
    }

    /**
     * Builds a directed graph from generated transport data:
     * {@code city -> list of departures}. Keys are city names.
     * Departures for each city are sorted by departure time.
     *
     * @param data the generated transport data
     * @return a map city → list of departures from that city
     */
    public static Map<String, List<Departure>> makeGraph(TransportDataGenerator.TransportData data) {
        Map<String, String> stationsToCity = new HashMap<>();
        for (TransportDataGenerator.Station s : data.stations) {
            stationsToCity.put(s.busStation, s.city);
            stationsToCity.put(s.trainStation, s.city);
        }

        Map<String, List<Departure>> graph = new HashMap<>();
        DateTimeFormatter HHmm = DateTimeFormatter.ofPattern("H:mm");

        for (TransportDataGenerator.Departure d : data.departures) {
            String from = stationsToCity.get(d.from);
            String to = d.to;

            if (from == null || to == null) {
                continue;
            }

            int departureTime = parseMinutes(d.departureTime, HHmm);
            Departure departure = new Departure(d.type, from, to, departureTime, d.duration, d.price, d.minTransferTime);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(departure);
        }
        graph.values().forEach(list -> list.sort(Comparator.comparingInt(Departure::getDepartureTime)));
        return graph;
    }

    @FunctionalInterface
    private interface Solver{
        Route solve(Map<String, List<Departure>> graph, String departure, String destination, int start, Set<Departure> bannedEdges, Set<String> bannedNodes);
    }

    /**
     * Returns up to {@code n} best routes according to the given criteria.
     * Internally uses a base solver (depending on the criteria) and generates
     * candidates by modifying prefixes of previously found routes (Yen-style approach).
     *
     * @param graph        the graph of departures (from {@link #makeGraph})
     * @param departure    starting city
     * @param destination  destination city
     * @param start        start time (minutes since midnight)
     * @param n            number of routes requested
     * @param criteria     optimization criteria
     * @return a list of routes sorted from best to worst, or an empty list if no route exists
     * @throws IllegalArgumentException if the criteria is not recognized
     */
    public static List<Route> nBestRoutes(Map<String, List<Departure>> graph, String departure, String destination,
                                          int start, int n, Criteria criteria) throws IllegalArgumentException{
        if(n <= 0){
            return List.of();
        }

        Solver solver;
        Comparator<Route> comparator;
        switch (criteria){
            case CHEAPEST:
                solver = Finder::cheapest;
                comparator = Comparator.comparingInt(Route::getTotalPrice);
                break;
            case FASTEST:
                solver = Finder::fastestBytime;
                comparator = Comparator.comparingInt(Route::getTotalTime);
                break;
            case MIN_TRANSFERS:
                solver = Finder::minTransfers;
                comparator = Comparator.comparingInt(Route::getNumberOfTransfers);
                break;
            default:
                throw new IllegalArgumentException("Unknown criteria!");
        }

        return findNBest(graph, departure, destination, start, n, solver, comparator);
    }

    /**
     * Finds the fastest route (primary: arrival time, secondary: number of transfers, third: ticket price).
     *
     * @param graph         graph of departures
     * @param departure     start city
     * @param destination   destination city
     * @param startMinutes  start time (minutes since midnight)
     * @param bannedEdges   edges to exclude (used for N-best search)
     * @param bannedNodes   nodes to exclude (used for N-best search)
     * @return the fastest route found, or {@link Route#nullRoute(String, String)} if unreachable
     */
    public static Route fastestBytime(Map<String, List<Departure>> graph,
                                      String departure, String destination,
                                      int startMinutes,
                                      Set<Departure> bannedEdges, Set<String> bannedNodes) {
        final int INF = Integer.MAX_VALUE / 4;

        final class Node {
            final String city;
            final int arrival;
            final int hops;
            final int price;
            Node(String c, int a, int h, int p) { city = c; arrival = a; hops = h; price = p; }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.<Node>comparingInt(n -> n.arrival)
                        .thenComparingInt(n -> n.hops)
                        .thenComparingInt(n -> n.price)
        );

        Map<String, Integer> bestArrival = new HashMap<>();
        Map<String, Integer> bestHopsAtBestArrival = new HashMap<>();
        Map<String, Integer> bestPriceAtBestArrivalAndHops = new HashMap<>();
        Map<String, Departure> previous = new HashMap<>();

        bestArrival.put(departure, startMinutes);
        bestHopsAtBestArrival.put(departure, 0);
        bestPriceAtBestArrivalAndHops.put(departure, 0);
        pq.add(new Node(departure, startMinutes, 0, 0));

        Node bestDestNode = null;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int knownArrHere   = bestArrival.getOrDefault(cur.city, INF);
            int knownHopsHere  = bestHopsAtBestArrival.getOrDefault(cur.city, INF);
            int knownPriceHere = bestPriceAtBestArrivalAndHops.getOrDefault(cur.city, INF);

            if (cur.arrival != knownArrHere ||
                    cur.hops    != knownHopsHere ||
                    cur.price   != knownPriceHere) {
                continue;
            }

            if (bestDestNode != null) {
                Node peek = pq.peek();
                if (peek == null ||
                        (peek.arrival > bestDestNode.arrival) ||
                        (peek.arrival == bestDestNode.arrival && peek.hops > bestDestNode.hops) ||
                        (peek.arrival == bestDestNode.arrival && peek.hops == bestDestNode.hops && peek.price >= bestDestNode.price)) {
                    break;
                }
            }

            for (Departure d : graph.getOrDefault(cur.city, Collections.emptyList())) {
                if (bannedEdges != null && bannedEdges.contains(d)) continue;
                if (bannedNodes != null && bannedNodes.contains(d.getTo())) continue;

                int transfer = cur.city.equals(departure) ? 0 : Math.max(0, d.getMinTransferWalkingTime());
                int earliest = cur.arrival + transfer;

                int dep = nextDeparture(d.getDepartureTime(), earliest);
                if (dep == Integer.MAX_VALUE) continue;
                int arr = dep + Math.max(1, d.getDuration());
                if (arr <= cur.arrival) continue;

                int newHops  = cur.hops + 1;
                int newPrice = cur.price + Math.max(0, d.getPrice());

                int knownArrTo   = bestArrival.getOrDefault(d.getTo(), INF);
                int knownHopsTo  = bestHopsAtBestArrival.getOrDefault(d.getTo(), INF);
                int knownPriceTo = bestPriceAtBestArrivalAndHops.getOrDefault(d.getTo(), INF);

                boolean improve =
                        (arr < knownArrTo) ||
                                (arr == knownArrTo && newHops < knownHopsTo) ||
                                (arr == knownArrTo && newHops == knownHopsTo && newPrice < knownPriceTo);

                if (improve) {
                    bestArrival.put(d.getTo(), arr);
                    bestHopsAtBestArrival.put(d.getTo(), newHops);
                    bestPriceAtBestArrivalAndHops.put(d.getTo(), newPrice);
                    previous.put(d.getTo(), d);

                    Node nx = new Node(d.getTo(), arr, newHops, newPrice);
                    pq.add(nx);

                    if (d.getTo().equals(destination)) {
                        if (bestDestNode == null ||
                                arr < bestDestNode.arrival ||
                                (arr == bestDestNode.arrival && newHops < bestDestNode.hops) ||
                                (arr == bestDestNode.arrival && newHops == bestDestNode.hops && newPrice < bestDestNode.price)) {
                            bestDestNode = nx;
                        }
                    }
                }
            }
        }

        Integer destArrival = bestArrival.get(destination);
        if (destArrival == null) {
            return Route.nullRoute(departure, destination);
        }

        List<Departure> trace = backtrack(previous, departure, destination);
        int totalTime = destArrival - startMinutes;
        int totalPrice = trace.stream().mapToInt(Departure::getPrice).sum();
        int numberOfTransfers = Math.max(0, trace.size() - 1);

        return new Route(departure, destination, trace, totalPrice, totalTime, numberOfTransfers);
    }


    /**
     * Finds the fastest route (primary: ticket price, secondary: number of transfers, third: arrival time).
     *
     */
    public static Route cheapest(Map<String, List<Departure>> graph,
                                 String departure, String destination, int startMinutes,
                                 Set<Departure> bannedEdges, Set<String> bannedNodes) {
        final int INF = Integer.MAX_VALUE / 4;

        final class State {
            final String city;
            final int price;
            final int arrival;
            final int hops;
            State(String c, int p, int a, int h) { city = c; price = p; arrival = a; hops = h; }
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.<State>comparingInt(s -> s.price)
                        .thenComparingInt(s -> s.hops)
                        .thenComparingInt(s -> s.arrival)
        );


        Map<String, Integer> bestPrice = new HashMap<>();
        Map<String, Integer> bestHopsAtBestPrice = new HashMap<>();
        Map<String, Integer> bestArrivalAtBestPrice = new HashMap<>();
        Map<String, Departure> previous = new HashMap<>();

        bestPrice.put(departure, 0);
        bestHopsAtBestPrice.put(departure, 0);
        bestArrivalAtBestPrice.put(departure, startMinutes);
        pq.add(new State(departure, 0, startMinutes, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            int knownPriceHere = bestPrice.getOrDefault(cur.city, INF);
            int knownHopsHere  = bestHopsAtBestPrice.getOrDefault(cur.city, INF);

            if (cur.price != knownPriceHere || cur.hops > knownHopsHere) continue;

            for (Departure d : graph.getOrDefault(cur.city, Collections.emptyList())) {
                if (bannedEdges != null && bannedEdges.contains(d)) continue;
                if (bannedNodes != null && bannedNodes.contains(d.getTo())) continue;

                int[] da = departArrive(departure, cur.city, cur.arrival, d);
                int nextArr = (da == null || da.length < 2) ? INF : da[1];
                if (nextArr >= INF) continue;
                if (nextArr <= cur.arrival) continue;

                int newPrice = cur.price + Math.max(0, d.getPrice());
                int newHops  = cur.hops + 1;

                Integer knownPriceTo = bestPrice.get(d.getTo());
                int knownHopsTo = bestHopsAtBestPrice.getOrDefault(d.getTo(), INF);

                if (knownPriceTo == null
                        || newPrice < knownPriceTo
                        || (newPrice == knownPriceTo && newHops < knownHopsTo)) {

                    bestPrice.put(d.getTo(), newPrice);
                    bestHopsAtBestPrice.put(d.getTo(), newHops);
                    bestArrivalAtBestPrice.put(d.getTo(), nextArr);
                    previous.put(d.getTo(), d);

                    pq.add(new State(d.getTo(), newPrice, nextArr, newHops));
                }
            }

            Integer bestDestPrice = bestPrice.get(destination);
            if (bestDestPrice != null) {
                State peek = pq.peek();
                if (peek == null || peek.price > bestDestPrice) break;
            }
        }

        Integer destPrice = bestPrice.get(destination);
        Integer destArrival = bestArrivalAtBestPrice.get(destination);
        if (destPrice == null || destArrival == null) {
            return Route.nullRoute(departure, destination);
        }

        List<Departure> trace = backtrack(previous, departure, destination);

        int totalPrice = destPrice;
        int totalTime = destArrival - startMinutes;
        int numberOfTransfers = Math.max(0, trace.size() - 1);

        return new Route(departure, destination, trace, totalPrice, totalTime, numberOfTransfers);
    }

    /**
     * Finds the route with the minimum number of transfers (BFS by transfer levels),
     * (primary: number of transfers, secondary: arrival time, third: ticket price).
     */
    public static Route minTransfers(Map<String, List<Departure>> graph,
                                     String departure, String destination,
                                     int startMinutes,
                                     Set<Departure> bannedEdges, Set<String> bannedNodes) {
        final int INF = Integer.MAX_VALUE / 4;

        final class Node {
            final String city;
            final int arrival;
            final int transfers;
            final int price;
            Node(String city, int arrival, int transfers, int price) {
                this.city = city; this.arrival = arrival; this.transfers = transfers; this.price = price;
            }
        }
        final class Key {
            final String city;
            final int transfers;
            Key(String city, int transfers) { this.city = city; this.transfers = transfers; }
            @Override public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Key)) return false;
                Key k = (Key) o; return transfers == k.transfers && Objects.equals(city, k.city);
            }
            @Override public int hashCode() { return Objects.hash(city, transfers); }
        }

        Map<Key, Integer> bestArrival = new HashMap<>();
        Map<Key, Integer> bestPriceAtBestArrival = new HashMap<>();

        Map<Key, Departure> previousEdge = new HashMap<>();
        Map<Key, Key> previousKey = new HashMap<>();

        ArrayDeque<Node> q = new ArrayDeque<>();

        Key startK = new Key(departure, 0);
        bestArrival.put(startK, startMinutes);
        bestPriceAtBestArrival.put(startK, 0);
        q.add(new Node(departure, startMinutes, 0, 0));

        int maxTransfers = Math.max(0, graph.size() - 1);

        Key bestGoal = null;
        int bestGoalArrival = INF;
        int bestGoalPrice = INF;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            Key levelBestGoal = null;
            int levelBestArrival = INF;
            int levelBestPrice = INF;

            for (int i = 0; i < levelSize; i++) {
                Node cur = q.poll();
                Key curK = new Key(cur.city, cur.transfers);

                int knownCurArr   = bestArrival.getOrDefault(curK, INF);
                int knownCurPrice = bestPriceAtBestArrival.getOrDefault(curK, INF);

                if (cur.arrival != knownCurArr || cur.price != knownCurPrice) continue;

                if (cur.transfers >= maxTransfers) continue;

                for (Departure d : graph.getOrDefault(cur.city, Collections.emptyList())) {
                    if (bannedEdges != null && bannedEdges.contains(d)) continue;
                    if (bannedNodes != null && bannedNodes.contains(d.getTo())) continue;

                    int[] da = departArrive(departure, cur.city, cur.arrival, d);
                    int arr = (da == null || da.length < 2) ? INF : da[1];
                    if (arr >= INF) continue;
                    if (arr <= cur.arrival) continue;

                    int newTransfers = cur.transfers + 1;
                    int newPrice = cur.price + Math.max(0, d.getPrice());

                    Key nxtK = new Key(d.getTo(), newTransfers);
                    int knownArr = bestArrival.getOrDefault(nxtK, INF);
                    int knownPrice = bestPriceAtBestArrival.getOrDefault(nxtK, INF);

                    boolean improves =
                            (arr < knownArr) ||
                                    (arr == knownArr && newPrice < knownPrice);

                    if (improves) {
                        bestArrival.put(nxtK, arr);
                        bestPriceAtBestArrival.put(nxtK, newPrice);
                        previousEdge.put(nxtK, d);
                        previousKey.put(nxtK, curK);
                        q.add(new Node(d.getTo(), arr, newTransfers, newPrice));
                    }

                    if (d.getTo().equals(destination)) {
                        if (arr < levelBestArrival ||
                                (arr == levelBestArrival && newPrice < levelBestPrice)) {
                            levelBestArrival = arr;
                            levelBestPrice = newPrice;
                            levelBestGoal = nxtK;
                        }
                    }
                }
            }

            if (levelBestGoal != null) {
                bestGoal = levelBestGoal;
                bestGoalArrival = levelBestArrival;
                bestGoalPrice = levelBestPrice;
                break;
            }
        }

        if (bestGoal == null) {
            return Route.nullRoute(departure, destination);
        }

        List<Departure> trace = new ArrayList<>();
        for (Key k = bestGoal; !k.equals(startK); ) {
            Departure d = previousEdge.get(k);
            if (d == null) return Route.nullRoute(departure, destination);
            trace.add(d);
            k = previousKey.get(k);
        }
        Collections.reverse(trace);

        int totalTransfers = Math.max(0, trace.size() - 1);
        int totalTime = bestGoalArrival - startMinutes;
        int totalPrice = bestGoalPrice;

        return new Route(departure, destination, trace, totalPrice, totalTime, totalTransfers);
    }


    /** Backtracks a path from a map of predecessors into a list of departures. */
    private static List<Departure> backtrack(Map<String, Departure> previous, String departure, String destination){
        List<Departure> trace = new ArrayList<>();
        String current = destination;
        while (!Objects.equals(current, departure)){
            Departure d = previous.get(current);
            if(d == null){
                return Collections.emptyList();
            }
            trace.add(d);
            current = d.getFrom();
        }
        Collections.reverse(trace);
        return trace;
    }
    /**
     * Core for N-best route finding: permutes prefixes of previous solutions,
     * bans corresponding edges/nodes, and uses the base solver to generate candidates.
     */
    private static List<Route> findNBest(Map<String, List<Departure>> graph,
                                         String departure, String destination,
                                         int start, int n,
                                         Solver solver, Comparator<Route> comparator){
        List<Route> A = new ArrayList<>();
        PriorityQueue<Route> B = new PriorityQueue<>(comparator);

        Route r1 = solver.solve(graph, departure, destination, start, Collections.emptySet(), Collections.emptySet());
        if(!r1.routeFound()){
            return List.of();
        }
        A.add(r1);
        for (int i = 1; i < n; i++) {
            Route r = A.get(i-1);
            for (int j = 0; j < r.getDepartures().size(); j++) {
                List<Departure> root = r.getDepartures().subList(0, j);
                int time = start;
                String city = departure;

                for(Departure d : root){
                    int[] da = departArrive(departure, city, time, d);
                    time = da[1];
                    city = d.getTo();
                }

                Set<Departure> bannedEdges = new HashSet<>();
                for(Route prev : A ){
                    if(sharesSamePrefix(prev, root) && prev.getDepartures().size()>j){
                        bannedEdges.add(prev.getDepartures().get(j));
                    }
                }

                Set<String> bannedNodes = new HashSet<>();
                for(Departure d : root){
                    bannedNodes.add(d.getFrom());
                }
                bannedNodes.remove(city);

                Route s = solver.solve(graph, city, destination, time, bannedEdges, bannedNodes);
                if(!s.routeFound()){
                    continue;
                }

                Route cand = concatRoutes(departure, destination, start, root, s);
                if(A.stream().anyMatch(rr -> sharesSameDepartures(rr, cand))){
                    continue;
                }
                B.add(cand);
            }
            if(B.isEmpty()){
                break;
            }
            A.add(B.poll());
        }
        return A;
    }

    /** Concatenates a prefix and a suffix route into a full route, recalculating total metrics. */
    private static Route concatRoutes(String departure, String destination, int start, List<Departure> root, Route s){
        List<Departure> joined = new ArrayList<>(root);
        joined.addAll(s.getDepartures());
        int t = start, price = 0;
        String city = departure;
        for(Departure d : joined){
            int[] da = departArrive(departure, city, t, d);
            t = da[1];
            price += d.getPrice();
            city = d.getTo();
        }
        int totalTime = t - start;
        int transfers = Math.max(0, joined.size()-1);
        return new Route(departure, destination, joined, price, totalTime, transfers);
    }

    /**
     * Checks whether a given route starts with exactly the same sequence
     * of departures as the provided prefix (compared by object reference).
     *
     * @param r      the route to check
     * @param prefix the prefix sequence of departures
     * @return {@code true} if {@code r} begins with all elements of {@code prefix},
     *         {@code false} otherwise
     */
    private static boolean sharesSamePrefix(Route r, List<Departure> prefix){
        List<Departure> d = r.getDepartures();
        if(d.size() < prefix.size()){
            return false;
        }
        for (int i = 0; i < prefix.size(); i++) {
            if(d.get(i) != prefix.get(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether two routes consist of exactly the same sequence
     * of departures (compared by object reference).
     *
     * @param a the first route
     * @param b the second route
     * @return {@code true} if both routes have the same length and the same
     *         departure objects at each position, {@code false} otherwise
     */
    private static boolean sharesSameDepartures(Route a, Route b){
        List<Departure> da = a.getDepartures();
        List<Departure> db = b.getDepartures();
        if(da.size() != db.size()){
            return false;
        }
        for (int i = 0; i < da.size(); i++) {
            if(da.get(i) != db.get(i)){
                return false;
            }
        }
        return true;
    }

    /** Computes the next real departure time ≥ {@code earliestAllowed}, wrapping around the day if necessary. */
    private static int nextDeparture(int depMin, int earliestAllowed) {
        final int DAY = 24 * 60;
        int dayStart = (earliestAllowed / DAY) * DAY;
        int tInDay   = earliestAllowed % DAY;
        return (depMin >= tInDay) ? (dayStart + depMin) : (dayStart + DAY + depMin);
    }

    /** Parses "HH:mm" into minutes since midnight. */
    private static int parseMinutes(String hhmm, DateTimeFormatter fmt) {
        LocalTime t = LocalTime.parse(hhmm, fmt);
        return t.getHour() * 60 + t.getMinute();
    }

    /**
     * Computes departure and arrival times for a given leg, considering
     * transfer penalty and daily cycles.
     *
     * @return array of length 2: {departure, arrival}
     */
    private static int[] departArrive(String source, String currentCity, int currentArrival, Departure d) {
        int transfer = currentCity.equals(source) ? 0 : Math.max(0, d.getMinTransferWalkingTime());
        int earliest = currentArrival + transfer;

        int dep = nextDeparture(d.getDepartureTime(), earliest);
        int dur = Math.max(1, d.getDuration());
        int arr = dep + dur;

        return new int[]{dep, arr};
    }

}
