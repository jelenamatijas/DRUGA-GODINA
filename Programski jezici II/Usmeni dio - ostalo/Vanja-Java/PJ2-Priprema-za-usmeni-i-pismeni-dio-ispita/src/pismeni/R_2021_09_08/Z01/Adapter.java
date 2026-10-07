package pismeni.R_2021_09_08.Z01;

public abstract class Adapter {

    static String PATH = "src/pismeni.R_2021_09_08/Z01/PJ2_exam_data";
    public abstract void importData(String fileName);

    public void addObject(String[] elements) {
        if (elements.length != 6)
            return;
        // tf102,S8,2,1530,Samsung,Telefon
        try {
            Proizvod p = new Proizvod(elements[0], elements[1],
                    Integer.parseInt(elements[2]), Double.parseDouble(elements[3]));
            Proizvodjac pr = new Proizvodjac(elements[4]);
            Vrsta v = new Vrsta(elements[5]);
            Main.podaci.add(p);
            Main.podaci.add(pr);
            Main.podaci.add(v);
        } catch (NumberFormatException e) {
            return;
        }
    }
}