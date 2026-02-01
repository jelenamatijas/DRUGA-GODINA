package pismeni.R_2022_06_15.Z03;

public class ExampleData2<T, V> implements Data<T, V> {
    public T type;
    public V value;
    public int size;

    public ExampleData2(T type, V value, int size) {
        this.type = type;
        this.value = value;
        this.size = size;
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