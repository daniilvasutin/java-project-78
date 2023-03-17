package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        Predicate<Object> isNumber = x -> x instanceof Integer;
        addPredicate(isNumber);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> isPositive = x -> x == null || x instanceof Integer && (Integer) x > 0;
        addPredicate(isPositive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> range = x -> x >= min && x <= max;
        addPredicate(range);
        return this;
    }


}
