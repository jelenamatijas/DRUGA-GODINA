public class Tred extends Thread
{
    public static void main(String [] args)
    {
 new Thread().start();
 Tred t = new Tred();
 t.start();
 double [] niz = new double[1];
 System.out.println(niz[0]);
 /*catch(Exception ex)
 {
   System.out.println("KRUTE");
 }*/
    }
}