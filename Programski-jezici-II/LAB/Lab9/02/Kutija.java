public class Kutija<T> {

    public T t;

    void setT(T t){
        this.t = t;
    }

    T getT(){
        return t;
    }
    public static void main(String[] args) {
        Kutija<Integer> kutija1 = new Kutija<Integer>();
        kutija1.setT(1);
        System.out.println(kutija1.getT());

        Kutija<String> kutija2 = new Kutija<String>();
        kutija2.setT("string");
        System.out.println(kutija2.getT());
    }
}