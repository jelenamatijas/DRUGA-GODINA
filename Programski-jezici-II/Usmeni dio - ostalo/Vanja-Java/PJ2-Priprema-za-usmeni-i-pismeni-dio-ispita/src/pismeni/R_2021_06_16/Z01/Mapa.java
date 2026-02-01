package pismeni.R_2021_06_16.Z01;

import java.util.Random;

public class Mapa {
    public static final int Y = 3;
    public static int X;
    public static Plovilo mapa[][];

    public static void main(String args[]) {
        try {
            if (Integer.parseInt(args[0]) <= 20 || Integer.parseInt(args[0]) >= 40)
                return;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        X = Integer.parseInt(args[0]);
        mapa = new Plovilo[X][Y];
        int y = new Random().nextInt(Y);
        Razarac r = new Razarac("Razarac", 0, y, true);
        boolean ok = false;
        while (!ok) {
            int y1 = new Random().nextInt(Y);
            if (y1 != y) {
                y = y1;
                ok = true;
            }
        }
        NosacAviona n = new NosacAviona("Nosac aviona", 0, y, true, false);
        y = new Random().nextInt(Y);
        Podmornica p1 = new Podmornica("Podmornica1", X - 1, y, true);
        ok = false;
        while (!ok) {
            int y1 = new Random().nextInt(Y);
            if (y1 != y) {
                y = y1;
                ok = true;
            }
        }
        Podmornica p2 = new Podmornica("Podmornica2", X - 1, y, true);
        r.start();
        n.start();
        p1.start();
        p2.start();
    }
}