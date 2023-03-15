package hexlet.code;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema{

    public NumberSchema() {
        Predicate<Object> isNumber = x -> x instanceof Integer;
        addPredicate(isNumber);
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = x -> x > 0;
        addPredicate(isPositive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> range = x -> x >= min && x <= max;
        addPredicate(range);
        return this;
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }
}
