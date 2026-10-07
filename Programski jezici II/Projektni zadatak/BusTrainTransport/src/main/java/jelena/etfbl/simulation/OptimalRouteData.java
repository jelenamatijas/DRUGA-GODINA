package jelena.etfbl.simulation;

/**
 * {@code OptimalRouteData} represents information about an optimal route
 * between two cities.
 *
 * <p>It stores:
 * <ul>
 *   <li>{@code departure} – the departure city,</li>
 *   <li>{@code destination} – the destination city,</li>
 *   <li>{@code price} – the total price of the route (as a string),</li>
 *   <li>{@code type} – the type of transport (e.g., "bus", "train").</li>
 * </ul>
 *
 * @author Jelena
 */
public class OptimalRouteData {
    private String departure;
    private String destination;
    private String price;
    private String type;

    /**
     * Constructs a new {@code OptimalRouteData} instance with the given parameters.
     *
     * @param departure   the departure city
     * @param destination the destination city
     * @param price       the price of the route (as a string)
     * @param type        the transport type (e.g., "bus", "train")
     */
    public OptimalRouteData(String departure, String destination, String price, String type){
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.type = type;
    }

    /** @return the departure city */
    public String getDeparture() {
        return departure;
    }

    /** @return the destination city */
    public String getDestination() {
        return destination;
    }

    /** @return the price of the route (string value) */
    public String getPrice() {
        return price;
    }

    /** @return the transport type (e.g., "bus", "train") */
    public String getType() {
        return type;
    }

    /** @param type sets the transport type */
    public void setType(String type) {
        this.type = type;
    }

    /** @param price sets the price of the route (string value) */
    public void setPrice(String price) {
        this.price = price;
    }

    /** @param destination sets the destination city */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /** @param departure sets the departure city */
    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
