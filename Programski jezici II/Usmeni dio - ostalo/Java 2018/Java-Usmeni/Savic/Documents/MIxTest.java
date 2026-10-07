public class MIxTest{
  public static void main(String atgs[]){
  GenericHolder<Integer> holder = new GenericHolder<Integer>();
  holder.set("10");
  System.out.println((Integer)holder.get());
}
}
  

class GenericHolder<T>{
 T v;

  public void set(T v){
  this.v = v;
  }

  public T get(){
  return v;
  }
}

