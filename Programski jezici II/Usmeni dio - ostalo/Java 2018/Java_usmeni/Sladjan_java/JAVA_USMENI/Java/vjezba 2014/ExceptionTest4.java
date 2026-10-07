
public class ExceptionTest4 {

 public static void main(String[] args) {
  System.out.println(method());
 }

 public static int method(){
try{ 
  throw new NullPointerException();
/*neki kod*/ 
}catch(NullPointerException e){
System.out.print("a "); 
}catch(ArrayIndexOutOfBoundsException e){
System.out.print("b "); 
}finally{
System.out.print("c ");
}
 return 1;}
}
