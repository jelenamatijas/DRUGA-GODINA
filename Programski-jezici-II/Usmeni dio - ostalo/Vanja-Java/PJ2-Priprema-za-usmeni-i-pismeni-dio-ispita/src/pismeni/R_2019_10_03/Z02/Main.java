package pismeni.R_2019_10_03.Z02;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) return;

        Prva prva = new Prva(args[0]);
        prva.start();
    }
}