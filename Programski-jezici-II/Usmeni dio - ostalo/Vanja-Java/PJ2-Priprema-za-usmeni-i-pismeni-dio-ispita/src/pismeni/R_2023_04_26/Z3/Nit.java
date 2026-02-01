package pismeni.R_2023_04_26.Z3;

import java.util.Random;
import java.util.stream.Collectors;

public class Nit extends Thread {

    static Integer FOUND = 0;
    int ident;

    public Nit(int id) {
        this.ident = id;
        start();
    }

    @Override
    public void run() {
        try {
            boolean start = (new Random().nextInt(2) == 0);
            while (!Main.END) {
                if (start) {
                    for (int i = 0; i < Main.studenti.size(); i++) {
                        boolean found = new Random().nextInt(100) < 2;
                        if (found) {
                            synchronized (FOUND) {
                                FOUND++;
                                Main.nagradjeni.add(Main.studenti.stream().collect(Collectors.toList()).get(i));
                                if (FOUND == 2) {
                                    Main.END = true;
                                    break;
                                }
                            }
                        }
                        if (FOUND >= 2) {
                            Main.END = true;
                            break;
                        }
                        System.out.println(this + " procesira " + Main.studenti.stream().collect(Collectors.toList()).get(i));
                        Thread.sleep(100);
                    }
                } else {
                    for (int i = Main.studenti.size() - 1; i > 0; i--) {
                        boolean found = new Random().nextInt(100) < 2;
                        if (found) {
                            synchronized (FOUND) {
                                FOUND++;
                                Main.nagradjeni.add(Main.studenti.stream().collect(Collectors.toList()).get(i));
                                if (FOUND == 2) {
                                    Main.END = true;
                                    break;
                                }
                            }
                        }
                        if (FOUND >= 2) {
                            Main.END = true;
                            break;
                        }
                        System.out.println(this + " procesira " + Main.studenti.stream().collect(Collectors.toList()).get(i));
                        Thread.sleep(100);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Nit " + this.ident;
    }
}