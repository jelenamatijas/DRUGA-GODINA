public class Simulacija3{

  public static void main(String args[]){
    go();
  }
  
  static void go() {
    int cows = 0;
    int[] twisters = {1,2,3};
    for(int i = 0; i < 4; i++)
      switch(twisters[i]) {
      case 2: cows++;
      case 1: cows += 10;
      case 0: go();
    }
    System.out.println(cows);
  }
  
}
