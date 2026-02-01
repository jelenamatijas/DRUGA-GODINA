package pismeni.R_2023_04_26.Z1;

import java.util.List;

public class PM extends Modul {

    List<Motor> motori;
    boolean status; // ima kvar
    boolean ukljucenRP = false; // raketni pogon

    public PM(List<Motor> motori) {
        this.motori = motori;
        setDaemon(true);
    }

    @Override
    public void run() {

        try {
            motori.stream().forEach(m -> m.start());
            ukljucenRP = true;
            while (!Main.STOP) {
                int brPokvarenih = (int) motori.stream().filter(m -> m.status.equals(Status.POKVAREN)).count();
                if (brPokvarenih > motori.size() / 2) {
                    status = true;
                    synchronized (Main.poruke) {
                        Main.poruke.add(new Poruka(Prioritet.KRITICNO, NM.poruke.get(Prioritet.KRITICNO)));
                        Main.STOP = true;
                        System.out.println("EVAKUACIJA");
                        break;
                    }
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}