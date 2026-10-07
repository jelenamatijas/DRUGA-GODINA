package pismeni.R_2022_02_09.Z01;

public class Autobus extends Vozilo {

    int brojMjesta;

    public Autobus(String id, Vozac vozac, Motor motor, String konfig, int brojMjesta) {
        super(id, vozac, motor, konfig);
        this.brojMjesta = brojMjesta;
        koefBrzine = 0.9;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + super.toString() + " broj mjesta " + brojMjesta;
    }
}