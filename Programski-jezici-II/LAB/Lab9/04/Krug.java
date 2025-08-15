public class Krug extends Oblik{
    double poluprecnik;

    Krug(){
        poluprecnik = 0.0;
    }

    Krug(double r){
        poluprecnik = r;
    }

    @Override
    public void iscrtaj(){
        System.out.println("Poluprecnik = " + poluprecnik);
    }

    @Override
    public double povrsina(){
        return Math.PI * Math.pow(poluprecnik,2);
    }
}
