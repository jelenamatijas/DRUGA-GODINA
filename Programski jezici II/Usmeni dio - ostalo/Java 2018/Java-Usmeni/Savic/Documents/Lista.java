import java.util.*;

public class Lista
{
    private class Cvor
    {
 private Cvor(Integer x)
 {
   this.x = x;
 }
 void setNext(Cvor next)
 {
   this.next = next;
 }
 Integer x;
 Cvor next;
    }
    private Cvor head;
    public Lista(int x)
    {
 head = this.new Cvor(x);
 head.setNext(null);
    }
    public void addNumber(int x)
    {
 Cvor tmp = this.new Cvor(x);
 tmp.setNext(head);
 head = tmp;
    }
    interface ListaIterator extends Iterator<Integer>
    {
 Integer daj();
    }
    public void printList()
    {
 ListaIterator iter = new ListaIterator(){
   Cvor trenutniCvor = Lista.this.head;
   public Integer daj()
   {
     Integer returnValue = trenutniCvor.x;
     trenutniCvor = trenutniCvor.next;
     return returnValue;
   }
   public void remove()
   {
   
   }
   public Integer next()
   {
     return null;
   }
   public boolean hasNext()
   {
     return false;
   }
 };
 try{
   while(true)
   {
     System.out.print(iter.daj() + " ");
   }
 }catch(NullPointerException ex)
 {
     System.out.println();
 }
    }
    public static void main(String [] args)
    {
 Lista lista = new Lista(0);
 for(int i = 10; i <= 21; i++)
 {
   lista.addNumber(i);
 }
 lista.printList();
    }
}