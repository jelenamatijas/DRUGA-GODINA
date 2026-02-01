public class Simulacija {
  public static void main(String args[]){
   
    
    int y=0, count=0;
    
    for(int x=3; x<6; x++){
      try{
        switch(x){
          case 3: count++;
          System.out.println(count+" 1 ");
          case 4: count++;
          System.out.println(count+" 2 ");
          case 7: count++;
          System.out.println(count+" 3 ");
          case 9: {
            y=7/(x-4);
            count+=10;
          }
          System.out.println(count+" 4 ");
        }
      }catch(Exception ex){
        count++;
        System.out.println(count+" 5 ");
      }
    }
      System.out.println(count+" 6 ");
    }
  }
