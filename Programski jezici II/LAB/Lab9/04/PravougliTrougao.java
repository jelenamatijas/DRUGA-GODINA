public class PravougliTrougao  extends Oblik{
    int a,b;

    PravougliTrougao(){
        a = 0;
        b = 0;
    }

    PravougliTrougao(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void iscrtaj(){
        System.out.println("a = " + a + "\nb = " + b);
    }

    @Override
    public double povrsina(){
        return a*b/2;
    }
}
