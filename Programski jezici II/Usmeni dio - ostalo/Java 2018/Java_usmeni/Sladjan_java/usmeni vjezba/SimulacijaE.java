public class SimulacijaE{
  
  public static void main(String args[]){
    
    int y, count = 0;
    for(int x = 3; x < 6; x++) {
      try {
        switch(x) {
          case 3: count++;
          
          case 4: count++;
          
          case 7: count++;
          
          case 9: { y = 7 / (x - 4); count += 10; } //kada x bude 4 NullPointerException i samo se u catch bloku uveca count i kraj
          
        }
      } catch (Exception ex) { System.out.println("Desio se exception"); count++;}
    }
    System.out.println(count);
  }
}

