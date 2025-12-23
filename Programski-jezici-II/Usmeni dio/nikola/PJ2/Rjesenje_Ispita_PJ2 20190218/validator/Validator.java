package validator;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Validator {

    public static <T> boolean validate(ArrayList<Predicate<T>> conditions, T item) {
        boolean isOk = true;
        for (Predicate<T> p : conditions) {
            isOk = isOk && p.test(item);
        }
        return isOk;
    }
}