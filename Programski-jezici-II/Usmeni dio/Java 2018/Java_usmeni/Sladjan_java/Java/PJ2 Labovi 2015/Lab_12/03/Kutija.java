class Kutija<T>{
  public T uKutiji;
  
  public T get(){
    return uKutiji;
  }
  
  public void set(T t){
    uKutiji=t;
  }
  
  public static void main(String args[]){
    Kutija<Integer> kI=new Kutija<Integer>();
    kI.set(551124);
    System.out.println("U kutiji je podatak "+kI.get()+" tipa "+kI.get().getClass());
    Kutija<String> kS=new Kutija<String>();
    kS.set(new String("student"));
    System.out.println("U kutiji je podatak "+kS.get()+" tipa "+kS.get().getClass());
  }
}