package net.etfbl.konkurentno.trka;

import java.util.Random;

public class Autotrka {
  
  public static void main(String[] args) {
    Random generatorSlucajnihBrojeva = new Random();
    // popunimo matricu
    Object[][] staza = new Object[3][15];
    
    // slucajno postavimo 4 "stop" znaka
    int brojZnakova = 4;
    while(brojZnakova > 0){
      int x = generatorSlucajnihBrojeva.nextInt(3);
      int y = generatorSlucajnihBrojeva.nextInt(15);
      if(staza[x][y] == null){
        brojZnakova--;
        staza[x][y] = "STOP";
      }
    }
    
    // kreiramo tri automobila
    System.out.println("*********ZAPOCINJE TRKA CITROEN-ovih OLDTIMER-a*************");
    Automobil a1 = new Automobil(staza, 0, "Spacek");
    Automobil a2 = new Automobil(staza, 1, "Diana");
    Automobil a3 = new Automobil(staza, 2, "Ajkula");
    // pokrecemo automobile
    a1.start();
    a2.start();
    a3.start();
    
  }
  
}
