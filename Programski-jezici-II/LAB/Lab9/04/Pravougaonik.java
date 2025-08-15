public class Pravougaonik extends Oblik{
    int a,b;

    Pravougaonik(){
        a = 0;
        b = 0;
    }

    Pravougaonik(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void iscrtaj(){
        System.out.println("a = " + a + "\nb = " + b);
        for(int i=0; i<b; i++){
            System.out.print(" * ");
            for(int j=0; j< a-2; j++){
                if((i == 0) || (i == b-1)){
                    System.out.print(" * ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println(" * ");;
        }
    }

    @Override
    public double povrsina(){
        return a*b;
    }
}
