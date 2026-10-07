class St
{
  public static void main(String[] args)
  {
    StringBuilder sb=new StringBuilder("Darko");
    StringBuffer buf=new StringBuffer("Darko");
    
    StringBuilder sd=new StringBuilder("Darko");
   
    if(sb.equals(sd))   
      System.out.println("Isti"); 
    else
      System.out.println("Nisu isti");
    
    String a="pero";
    String b="kovljenic";
  
  StringBuilder nova;
  nova=sb;
  if(nova==sb)
  {
    System.out.println("reference nova i sb su iste,tj referenciraju isti objekat");
  }
  nova=sb.deleteCharAt(3);
  System.out.println(nova);
  
  if(nova.equals(sb))
  {
    System.out.println("reference nova i sb su iste,tj referenciraju isti objekat");
  }
  else
  {  
    System.out.println("reference nova i sb nisu iste");
  }
  }
}
