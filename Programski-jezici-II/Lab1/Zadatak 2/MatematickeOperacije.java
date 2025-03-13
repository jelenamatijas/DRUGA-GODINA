class MatematickeOperacije {

    int operand1;
    int operand2;
    
    public MatematickeOperacije(){
      operand1 = 1;
      operand2 = 2;
    }
    
    
    public MatematickeOperacije(int op1, int op2) {
        operand1 = op1;
        operand2 = op2;
    }
    
    /*
    public MatematickeOperacije(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
    */
    
    public int proizvod(){
        return operand1 * operand2;
    }
    
    public double kolicnik(){
        //return operand1/operand2;
        return (double) operand1/operand2;
    }
    
    public boolean prviJeVeci(){
      return operand1>operand2;
    }
    
    public int sumaPrvih20CijelihBrojeva() {
        int rezultat = 0;
        for(int i = 1; i<21; i++) {
          rezultat += i;
        }
        return rezultat;    
    }
    
    public int razlikaBrojevaDjeljivihSa3() {
        int razlika=100;
        for(int i=100; i>0; i--){
          if(i%3==0)
              razlika-=i;
          }
        return razlika;
    }
    
    public static void main(String args[]){
        MatematickeOperacije test = new MatematickeOperacije();
        System.out.println(test.operand1+" "+test.operand2);
        System.out.println("a."+test.proizvod());
        System.out.println("b."+test.kolicnik());
        System.out.println("c."+test.prviJeVeci());
        System.out.println("d."+test.sumaPrvih20CijelihBrojeva());
        MatematickeOperacije test2 = new MatematickeOperacije(80, 78);
        System.out.println(test2.operand1+" "+test2.operand2);
        System.out.println("a."+test2.proizvod());
        System.out.println("b."+test2.kolicnik());
        System.out.println("c."+test2.prviJeVeci());
        System.out.println("e."+test2.razlikaBrojevaDjeljivihSa3());
    }         
    
}
