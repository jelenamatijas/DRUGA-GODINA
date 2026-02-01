/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package izuzetak;

/**
 *
 * @author Milan
 */
public class PredmetException extends Exception {

    /**
     * Creates a new instance of <code>PredmetException</code> without detail
     * message.
     */
    public PredmetException() {
    }

    /**
     * Constructs an instance of <code>PredmetException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PredmetException(String msg) {
        super(msg);
    }
}
