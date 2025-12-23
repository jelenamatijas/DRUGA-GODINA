package fabrika;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class Proizvod implements Serializable {

    public String naziv;
    public String serijskiBroj;
    public boolean imaGresku;

    public Proizvod(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Proizvod{" + "naziv=" + naziv + '}';
    }

}
