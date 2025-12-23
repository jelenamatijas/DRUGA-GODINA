class S
{
  static String y;
  public static void main(String[] args)
  {
    
    
    S r=new S();
    
    String a="Darko";
    String b="Darko";   
    String c="Dar"+"ko"; 
    
    String temp="Dar";
    String s=temp+"ko";
    
       String d=new String("Darko");
       String e=new String("Darko");
       
       if(a==c) {
         System.out.println(" tacno");
       }
       else {
         System.out.println(" netacno");
       }
       
       
       if(d.equals(e)) 
       {
         System.out.println("true");
       }
       else
       {
         System.out.println("false");
       }
       if(a==b) 
        System.out.println("Reference jednake");
       else
         System.out.println("Reference razlicite");
    
       if(d==e) 
        System.out.println("Reference jednake");
       else
         System.out.println("Reference razlicite");
       
       if(a instanceof Object)
       System.out.println("a je objekat..."); 
       
       String x="";
       if(x instanceof Object)
         System.out.println("x je objekat...");
       
       
       
      System.out.println("Y="+y);
        if(y instanceof Object)
         System.out.println("y je objekat...");
       
  }
}