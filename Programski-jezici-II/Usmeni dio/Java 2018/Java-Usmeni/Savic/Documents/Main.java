public class Main
{
  public static void main(String [] args)
  {
      //Sandwich s = new Sandwich(new Bread(10), new Salad(2), new Meat(200));
      Sandwich s = new Sandwich();
      //while(s.eat());
  }
}

interface Eatable
{
  boolean eat();
  class Supak{
  
  }
}

abstract class Food implements Eatable
{
  static
  {
    System.out.println("Food static.");
  }
  {
    System.out.println("nestatic food.");
  }
  private int amount;
  public Food(int amount)
  {
    this.amount = amount;
    System.out.println("Food konstruktor");
  }
  public int getAmount()
  {
    return amount;
  }
  public boolean eat()
  {
    return (--amount > 0);
  }
}

class Meat extends Food
{
  public Meat(int amount)
  {
    super(amount);
    System.out.println("Konstruktor meat");
  }
  static
  {
    System.out.println("Meat static.");
  }
  {
    System.out.println("nestatic meat.");
  }
}

class Salad extends Food
{
  public Salad(int amount)
  {
    super(amount);
    System.out.println("Konstruktor salad");
  }
  static
  {
    System.out.println("Salad static.");
  }
  {
    System.out.println("nestatic salad.");
  }
}

class Bread extends Food
{
  public Bread(int amount)
  {
    super(amount);
    System.out.println("Konstruktor bread");
  }
  static
  {
    System.out.println("Bread static.");
  }
  {
    System.out.println("nestatic bread.");
  }
}

class Sandwich implements Eatable
{
  /*public Sandwich(Bread b, Salad s, Meat m)
  {
    this.b = b;
    this.s = s;
    this.m = m;
    System.out.println("Konstruktor Sandwich");
  }*/
  Bread b = new Bread(0);
  Salad s = new Salad(0);
  Meat m = new Meat(0);
  static
  {
    System.out.println("Sandwich static.");
  }
  {
    System.out.println("nestatic sandwich.");
  }
  public boolean eat()
  {
      System.out.println("" + b.getAmount() + " " + s.getAmount() + " " + m.getAmount());
      return b.eat() | s.eat() | m.eat();
  }
}