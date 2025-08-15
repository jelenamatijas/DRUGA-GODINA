import java.util.Random;
import java.util.Scanner;

public class CounterMain {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Unesite broj niti: ");
        int num = scanner.nextInt();
        scanner.close();

        Random rand = new Random();
        for(int i=0; i<num; i++){
            new Counter("Nit_" + i, (rand.nextInt(5001)+5000)).start();
        }
    }
}
