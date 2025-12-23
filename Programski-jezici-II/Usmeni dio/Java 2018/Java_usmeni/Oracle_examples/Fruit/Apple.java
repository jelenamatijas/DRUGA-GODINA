import food.Fruit;

class Apple extends Fruit implements Eatable{
  
  public void eat(){
    System.out.println("eat");
  }
  
  public void eat2(){
    System.out.println("eat2");
  }
}