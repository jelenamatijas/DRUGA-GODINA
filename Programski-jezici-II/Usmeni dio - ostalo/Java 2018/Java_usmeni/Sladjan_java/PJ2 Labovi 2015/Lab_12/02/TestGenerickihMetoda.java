import java.util.Arrays;
class TestGenerickihMetoda{
  public static <E> void ispisiNiz(E[] ulazniNiz){
    System.out.println(Arrays.toString(ulazniNiz));
    //ili
    /*for(E element: ulazniNiz)
      System.out.print(element+" ");*/
  }
  
  public static void main(String args[]){
    Integer[] nizCijelihPodataka = {1,2,3,4,5,6,7};
    TestGenerickihMetoda.ispisiNiz(nizCijelihPodataka);
    String[] nizStringova = {"student", "etf", "bl"};
    TestGenerickihMetoda.ispisiNiz(nizStringova);
  }
}