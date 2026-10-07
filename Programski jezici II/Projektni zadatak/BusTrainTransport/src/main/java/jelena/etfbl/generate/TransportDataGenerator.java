package jelena.etfbl.generate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * {@code TransportDataGenerator} produces synthetic transport data for a grid of cities,
 * including bus/train stations and random departures between neighboring cities.
 * <p>
 * The generator creates:
 * <ul>
 *   <li>{@link TransportData#countryMap} – a grid of city names {@code G_x_y},</li>
 *   <li>{@link TransportData#stations} – bus ({@code A_x_y}) and train ({@code Z_x_y}) stations per city,</li>
 *   <li>{@link TransportData#departures} – randomized departures (type, from, to, time, duration, price, transfer time).</li>
 * </ul>
 * Data is saved to a JSON file via {@link #saveToJson(TransportData, String)}.
 *
 * @author etfbl
 */
public class TransportDataGenerator {
    private int N;                      // rows
    private int M;                      // cols
    private int DEPARTURES_PER_STATION; // departures per station (bus + train)
    private static final Random random = new Random();

    /**
     * Constructs a generator for an {@code N x M} grid with a fixed number of departures per station.
     *
     * @param n          number of rows
     * @param m          number of columns
     * @param departues  departures per station (kept as-is to match existing code)
     */
    public TransportDataGenerator(int n, int m, int departues) {
        N = n;
        M = m;
        DEPARTURES_PER_STATION = departues;
    }

    /**
     * CLI entry point.
     * <p>Usage: {@code java TransportDataGenerator <rows> <cols> <departuresPerStation>}</p>
     * Generates data and writes it to {@code transport_data.json}.
     *
     * @param args three integers: rows, cols, departuresPerStation
     * @throws NumberFormatException if arguments are not valid integers
     */
    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int departures = Integer.parseInt(args[2]);
        TransportDataGenerator generator = new TransportDataGenerator(rows, cols, departures);
        TransportData data = generator.generateData();
        generator.saveToJson(data, "transport_data.json");
        System.out.println("Podaci su generisani i sacuvani kao transport_data.json");
    }

    /**
     * Container for all generated data: the country grid, stations, and departures.
     */
    public static class TransportData {
        /** City grid, each cell holds a city name {@code G_x_y}. */
        public String[][] countryMap;
        /** All stations, one bus and one train station per city. */
        public List<Station> stations;
        /** All generated departures (bus and train). */
        public List<Departure> departures;
    }

    /**
     * A station entry for a city: bus and train station identifiers.
     */
    public static class Station {
        /** City name, e.g. {@code G_0_0}. */
        public String city;
        /** Bus station name, e.g. {@code A_0_0}. */
        public String busStation;
        /** Train station name, e.g. {@code Z_0_0}. */
        public String trainStation;
    }

    /**
     * A single departure (bus or train) from one city to a neighboring city.
     */
    public static class Departure {
        /** Transport type: {@code "autobus"} or {@code "voz"}. */
        public String type;
        /** Source station (bus or train), e.g. {@code A_0_0} or {@code Z_0_0}. */
        public String from;
        /** Destination city name, e.g. {@code G_0_1}. */
        public String to;
        /** Departure time in {@code HH:mm} (24h). */
        public String departureTime;
        /** Travel duration in minutes. */
        public int duration;
        /** Ticket price (integer monetary units). */
        public int price;
        /** Minimum transfer time in minutes. */
        public int minTransferTime;
    }

    /**
     * Generates the full dataset: grid, stations, and departures.
     *
     * @return populated {@link TransportData}
     */
    public TransportData generateData() {
        TransportData data = new TransportData();
        data.countryMap = generateCountryMap();
        data.stations = generateStations();
        data.departures = generateDepartures(data.stations);
        return data;
    }

    /**
     * Generates the city grid {@code G_x_y} of size {@code N x M}.
     */
    private String[][] generateCountryMap() {
        String[][] countryMap = new String[N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                countryMap[x][y] = "G_" + x + "_" + y;
            }
        }
        return countryMap;
    }

    /**
     * Generates bus and train stations for every city in the grid.
     */
    private List<Station> generateStations() {
        List<Station> stations = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                Station station = new Station();
                station.city = "G_" + x + "_" + y;
                station.busStation = "A_" + x + "_" + y;
                station.trainStation = "Z_" + x + "_" + y;
                stations.add(station);
            }
        }
        return stations;
    }

    /**
     * Generates departures for each station (both bus and train).
     * Each departure goes to one of the four cardinal neighbors, if any.
     *
     * @param stations list of all stations
     * @return list of randomized departures
     */
    private List<Departure> generateDepartures(List<Station> stations) {
        List<Departure> departures = new ArrayList<>();

        for (Station station : stations) {
            int x = Integer.parseInt(station.city.split("_")[1]);
            int y = Integer.parseInt(station.city.split("_")[2]);

            // bus departures
            for (int i = 0; i < DEPARTURES_PER_STATION; i++) {
                departures.add(generateDeparture("autobus", station.busStation, x, y));
            }

            // train departures
            for (int i = 0; i < DEPARTURES_PER_STATION; i++) {
                departures.add(generateDeparture("voz", station.trainStation, x, y));
            }
        }
        return departures;
    }

    /**
     * Generates a single randomized departure from (x,y) to a neighboring city.
     *
     * @param type transport type ({@code "autobus"} or {@code "voz"})
     * @param from source station identifier
     * @param x    source row in the grid
     * @param y    source column in the grid
     * @return populated {@link Departure}
     */
    private Departure generateDeparture(String type, String from, int x, int y) {
        Departure departure = new Departure();
        departure.type = type;
        departure.from = from;

        // pick a neighbor city as destination
        List<String> neighbors = getNeighbors(x, y);
        departure.to = neighbors.isEmpty() ? from : neighbors.get(random.nextInt(neighbors.size()));

        // departure time on 15-minute steps
        int hour = random.nextInt(24);
        int minute = random.nextInt(4) * 15; // 0, 15, 30, 45
        departure.departureTime = String.format("%02d:%02d", hour, minute);

        // duration (30–180 min) and price (100–1000)
        departure.duration = 30 + random.nextInt(151);
        departure.price = 100 + random.nextInt(901);

        // minimum transfer time (5–30 min)
        departure.minTransferTime = 5 + random.nextInt(26);

        return departure;
    }

    /**
     * Returns 4-neighborhood (up, down, left, right) within the grid bounds,
     * as city names {@code G_nx_ny}.
     */
    private List<String> getNeighbors(int x, int y) {
        List<String> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                neighbors.add("G_" + nx + "_" + ny);
            }
        }
        return neighbors;
    }

    /**
     * Serializes {@link TransportData} to a JSON file. This is a minimal hand-rolled serializer
     * tailored to the {@code TransportData} structure.
     *
     * @param data     the data to save
     * @param filename output JSON file path
     */
    private void saveToJson(TransportData data, String filename) {
        try (FileWriter file = new FileWriter(filename)) {
            StringBuilder json = new StringBuilder();
            json.append("{\n");

            // countryMap
            json.append("  \"countryMap\": [\n");
            for (int i = 0; i < N; i++) {
                json.append("    [");
                for (int j = 0; j < M; j++) {
                    json.append("\"").append(data.countryMap[i][j]).append("\"");
                    if (j < M - 1) json.append(", ");
                }
                json.append("]");
                if (i < N - 1) json.append(",");
                json.append("\n");
            }
            json.append("  ],\n");

            // stations
            json.append("  \"stations\": [\n");
            for (int i = 0; i < data.stations.size(); i++) {
                Station s = data.stations.get(i);
                json.append("    {\"city\": \"").append(s.city)
                        .append("\", \"busStation\": \"").append(s.busStation)
                        .append("\", \"trainStation\": \"").append(s.trainStation)
                        .append("\"}");
                if (i < data.stations.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("  ],\n");

            // departures
            json.append("  \"departures\": [\n");
            for (int i = 0; i < data.departures.size(); i++) {
                Departure d = data.departures.get(i);
                json.append("    {\"type\": \"").append(d.type)
                        .append("\", \"from\": \"").append(d.from)
                        .append("\", \"to\": \"").append(d.to)
                        .append("\", \"departureTime\": \"").append(d.departureTime)
                        .append("\", \"duration\": ").append(d.duration)
                        .append(", \"price\": ").append(d.price)
                        .append(", \"minTransferTime\": ").append(d.minTransferTime)
                        .append("}");
                if (i < data.departures.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("  ]\n");

            json.append("}");
            file.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
