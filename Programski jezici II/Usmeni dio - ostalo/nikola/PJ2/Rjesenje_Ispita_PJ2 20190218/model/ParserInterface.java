package model; 

public interface ParserInterface<T> {
    T parse(String s);
}