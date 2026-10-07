public class Simulacija2{
  
  public static void main(String args[]){
    
    int x=3;
    for(int i=0; i<3; i++){
      if(i==1) x=x++;
       System.out.println(i+" "+x+" ");
      if(i%2==0 && x%2==0) System.out.print(".");
       System.out.println(i+" "+x+" ");
      if(i%2==0 && x%2==1) System.out.print("-");
       System.out.println(i+" "+x+" ");
      if(i==2 ^ x==4)System.out.print(",");
       System.out.println(i+" "+x+" ");
    }
    System.out.println("<");
  }
}
