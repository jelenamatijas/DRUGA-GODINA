package pismeni.R_2022_06_15.Z03;

public class ExampleData1<T, V> implements Data<T, V> {
    public T type;
    public V value;
    public String color;


    public ExampleData1(T type, V value, String color) {
        this.type = type;
        this.value = value;
        this.color = color;
    }

    @Override
    public T getType() {
        return type;
    }

    @Override
    public V getValue() {
        return value;
    }
}