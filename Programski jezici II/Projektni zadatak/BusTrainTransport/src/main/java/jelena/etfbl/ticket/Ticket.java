package jelena.etfbl.ticket;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@code Ticket} is a simple serializable data holder for storing information
 * about a purchased route in the transport system.
 *
 * <p>The data is stored as a {@link Map} of key–value pairs. By default,
 * the constructor initializes the following keys:
 * <ul>
 *   <li>{@code route} – the full route description (e.g., "A -> B -> C"),</li>
 *   <li>{@code time} – the departure time,</li>
 *   <li>{@code price} – the price of the ticket,</li>
 *   <li>{@code date} – the purchase date.</li>
 * </ul>
 *
 * @author Jelena
 */
public class Ticket implements Serializable {
    private Map<String, String> data = new LinkedHashMap<>();

    /**
     * Constructs a new {@code Ticket} with the given route details.
     *
     * @param route route description (e.g., "CityA -> CityB -> CityC")
     * @param time  departure time
     * @param price ticket price
     * @param date  purchase date
     */
    public Ticket(String route, String time, String price, String date){
        data.put("route", route);
        data.put("time", time);
        data.put("price", price);
        data.put("date", date);
    }

    /**
     * Retrieves a stored value by its key.
     *
     * @param key the key (e.g., "route", "time", "price", "date")
     * @return the value associated with the key, or {@code null} if not present
     */
    public String getValue(String key){
        return data.get(key);
    }

    /**
     * @return the full ticket data as a key–value map
     */
    public Map<String, String> getData(){
        return data;
    }

    /**
     * Replaces the current ticket data with the provided map.
     *
     * @param data the new data map to set
     */
    public void setData(Map<String, String> data){
        this.data = data;
    }

}
