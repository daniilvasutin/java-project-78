package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        Predicate<Object> isString = x -> x instanceof String;
        addPredicate(isString);

        Predicate<Object> NotNullNotEmpty = x -> x != null && !x.equals("");
        addPredicate(NotNullNotEmpty);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = x -> x.contains(str);
        addPredicate(contains);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> minLength = x -> x.length() >= length;
        addPredicate(minLength);
        return this;
    }

}
