package jelena.etfbl.simulation;

/**
 * {@code Departure} represents a single transport departure in the simulation.
 * <p>
 * Each departure has attributes such as type (bus, train, etc.), departure
 * and arrival stations, departure time, duration, price, and minimum transfer time.
 * Additionally, there is a static {@code WALKING} constant that represents
 * the time spend transferring from one station to another in the same city.
 * </p>
 *
 * @author Jelena
 */
public class Departure {
    private String type;
    private String from;
    private String to;
    private int departureTime;
    private int duration;
    private int price;
    private int minTransferTime;

    /** time spend transferring from one station to another in the same city
     *  (in minutes) added to transfer time. */
    private static int WALKING = 2;

    /**
     * Creates a new {@code Departure} instance.
     *
     * @param type            the type of transport (e.g. "bus", "train")
     * @param from            the departure city
     * @param to              the destination city
     * @param departureTime   the departure time (minutes since midnight)
     * @param duration        the travel duration in minutes
     * @param price           the ticket price
     * @param minTransferTime the minimum transfer time in minutes
     */
    public Departure(String type, String from, String to, int departureTime,
                     int duration, int price, int minTransferTime){
        this.type = type;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.duration = duration;
        this.price = price;
        this.minTransferTime = minTransferTime;
    }

    /** @return departure time (minutes since midnight) */
    public int getDepartureTime() {
        return departureTime;
    }

    /** @return duration of travel in minutes */
    public int getDuration() {
        return duration;
    }

    /** @return destination city */
    public String getTo() {
        return to;
    }

    /** @return departure city */
    public String getFrom() {
        return from;
    }

    /** @return ticket price */
    public int getPrice() {
        return price;
    }

    /** @return type of transport (e.g. "bus", "train") */
    public String getType() {
        return type;
    }

    /** @return minimum transfer time in minutes */
    public int getMinTransferTime() {
        return minTransferTime;
    }

    /** @return minimum transfer time + walking time */
    public int getMinTransferWalkingTime() {
        return minTransferTime + WALKING;
    }

    /** @return walking time (in minutes) */
    public int getWALKING() {
        return WALKING;
    }

    /** @param departureTime new departure time in minutes */
    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    /** @param duration new duration of travel in minutes */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /** @param type new type of transport (e.g. "bus", "train") */
    public void setType(String type) {
        this.type = type;
    }

    /** @param to new destination city */
    public void setTo(String to) {
        this.to = to;
    }

    /** @param from new departure city */
    public void setFrom(String from) {
        this.from = from;
    }

    /** @param price new ticket price */
    public void setPrice(int price) {
        this.price = price;
    }

    /** @param minTransferTime new minimum transfer time in minutes */
    public void setMinTransferTime(int minTransferTime) {
        this.minTransferTime = minTransferTime;
    }

    /**
     * Updates the walking time applied to transfers.
     *
     * @param walking new walking time in minutes
     */
    public void setWALKING(int walking) {
        WALKING = walking;
    }


}
