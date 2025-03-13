class InicijalizacijaPromjenljivih {
  public static void main(String args[]){
      int vrijednost = 7, duplaVrijednost = vrijednost * 2;
      double kolicnikSaDva = vrijednost/2.0;
      char znakPodatka = 'i';
      
      System.out.println("vrijednost = " + vrijednost + "\n"
                        + "duplaVrijednost = " + duplaVrijednost + "\n"
                        + "kolicnikSaDva = " + kolicnikSaDva + "\n"
                        + "znakPodatka = " + znakPodatka 
      );
      
      //kastovanje
      long vrijednostL = vrijednost;
      short vrijednostS = (short) vrijednost;
      System.out.println("vrijednostL = " + vrijednostL);
      System.out.println("vrijednostS = " + vrijednostS);
      
     // float kolicnikSaDvaF = kolicnikSaDva;
      float kolicnikSaDvaF = (float) kolicnikSaDva;
      System.out.println("kolicnikSaDvaF = " + kolicnikSaDva);
  }

}
