/** @author Milan @version 1.1 {www.mboookcase.com}*/
public class MatematickeOperacije {
  double operand1, operand2;
  MatematickeOperacije (double o1, double o2){
    operand1=o1;
    operand2=o2;
  }
  MatematickeOperacije(){
    operand1=Math.random()*10;
    operand1=Math.random()*10;
  }
  public int proizvod (){
    return (int)(operand1*operand2);
  }
  public float kolicnik (){
    return (float)(operand1/operand2);
  }
  public boolean prviJeVeci(){
    return operand1>operand2;
    
  }
  public int sumaPrvih20CijelihBrojeva(){
    int sum=0;
    for(int i=1;i<21;i++)sum+=i;
    return sum;
  }
  public int razlikaBrojevaDjeljivihSa3(){
    int razlika=0;
    int i=100;
    for(;i>0;i--)
      if(i%3==0){
      razlika=i;
      break;
    }
    for (;i>0;i--)
      if(i%3==0)razlika-=i;
    return razlika;
  }
  
  public static void main(String[] args){
  MatematickeOperacije a= new MatematickeOperacije();
  MatematickeOperacije b= new MatematickeOperacije(4,2);
  
  System.out.println(a.proizvod() + "-a.proizvod");
  System.out.println(a.kolicnik() + "-a.kolicnik");
  System.out.println(a.proizvod() );
  System.out.println(a.sumaPrvih20CijelihBrojeva() + "-a.Suma prvih 20");
  System.out.println(a.razlikaBrojevaDjeljivihSa3() + "-a. Razlika djeljivih sa 3");
  
  
  System.out.println(b.proizvod() + "-b.proizvod");
  System.out.println(b.kolicnik() + "-b.kolicnik");
  System.out.println(b.proizvod() + "-b.prviJeVeci");
  System.out.println(b.sumaPrvih20CijelihBrojeva() + "-b.Suma prvih 20");
  System.out.println(b.razlikaBrojevaDjeljivihSa3() + "-b. Razlika djeljivih sa 3");
  }
  
}
