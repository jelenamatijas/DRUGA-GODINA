import java.util.Arrays;

public class TestGenerickihMetoda {
    public static <E> void ispisNiz(E[] ulazniNiz){
        System.out.println(Arrays.toString(ulazniNiz));
    }

    public static void main(String []args){
        Integer numbers[] = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        String strings[] = new String[]{"a", "b", "c"};

        ispisNiz(numbers);
        ispisNiz(strings);
    }
}
