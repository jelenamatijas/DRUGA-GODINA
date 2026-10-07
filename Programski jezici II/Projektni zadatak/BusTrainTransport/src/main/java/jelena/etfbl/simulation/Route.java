package jelena.etfbl.simulation;

import java.util.List;

/**
 * {@code Route} represents a complete path between two cities in the transport system.
 * <p>
 * A route consists of:
 * <ul>
 *   <li>{@code departure} – the starting city,</li>
 *   <li>{@code destination} – the destination city,</li>
 *   <li>{@code departures} – the sequence of {@link Departure} legs making up the route,</li>
 *   <li>{@code totalPrice} – the sum of ticket prices for the entire route,</li>
 *   <li>{@code totalTime} – the total travel time in minutes,</li>
 *   <li>{@code numberOfTransfers} – the number of transfers (legs - 1).</li>
 * </ul>
 *
 * @author Jelena
 */
public class Route {
    private String departure;
    private String destination;
    private List<Departure> departures;
    private int totalPrice;
    private int totalTime;
    private int numberOfTransfers;

    /**
     * Constructs a new {@code Route}.
     *
     * @param departure         the departure city
     * @param destination       the destination city
     * @param departures        the list of departures (legs of the route)
     * @param totalPrice        total price of the route
     * @param totalTime         total travel time in minutes
     * @param numberOfTransfers number of transfers (legs - 1)
     */
    public Route(String departure, String destination, List<Departure> departures,
                 int totalPrice, int totalTime, int numberOfTransfers){
        this.departure = departure;
        this.destination = destination;
        this.departures = departures;
        this.totalPrice = totalPrice;
        this.totalTime = totalTime;
        this.numberOfTransfers = numberOfTransfers;
    }

    /**
     * Checks if this route is valid (at least one departure exists).
     *
     * @return {@code true} if the route has at least one departure, {@code false} otherwise
     */
    public boolean routeFound(){
        return !departures.isEmpty();
    }

    /**
     * Creates a {@code nullRoute}, representing an empty/invalid route
     * between the given departure and destination.
     *
     * @param dep   departure city
     * @param dest  destination city
     * @return a {@code Route} object with no departures and zero values
     */
    public static Route nullRoute(String dep, String dest){
        return new Route(dep, dest, List.of(), 0, 0, 0);
    }

    /**
     * Converts the total travel time in minutes into a formatted string "HHh MMmin".
     * Example: {@code 135 minutes -> "02h 15min"}.
     *
     * @return the formatted travel time string
     */
    public String getTransformedTime(){
        String s = (totalTime/60 >= 10) ? String.valueOf(totalTime/60) : ("0" + String.valueOf(totalTime/60));
        s += "h ";
        s += (totalTime - ((totalTime/60)*60) < 10) ? (String.valueOf("0" + (totalTime - ((totalTime/60)*60)))) : String.valueOf(totalTime - ((totalTime/60)*60));
        s += "min";
        return s;
    }


    /** @return the total price of the route */
    public int getTotalPrice() {
        return totalPrice;
    }

    /** @return the departure city */
    public String getDeparture() {
        return departure;
    }

    /** @return the destination city */
    public String getDestination() {
        return destination;
    }

    /** @return the total travel time in minutes */
    public int getTotalTime() {
        return totalTime;
    }

    /** @return the number of transfers */
    public int getNumberOfTransfers() {
        return numberOfTransfers;
    }

    /** @return the list of departures (legs of this route) */
    public List<Departure> getDepartures() {
        return departures;
    }

    /** @param departure sets the departure city */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /** @param destination sets the destination city */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /** @param totalPrice sets the total price of the route */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /** @param totalTime sets the total travel time in minutes */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /** @param departures sets the list of departures (legs of the route) */
    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    /** @param numberOfTransfers sets the number of transfers */
    public void setNumberOfTransfers(int numberOfTransfers) {
        this.numberOfTransfers = numberOfTransfers;
    }
}
