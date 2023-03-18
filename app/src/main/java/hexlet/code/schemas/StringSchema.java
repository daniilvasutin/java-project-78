package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        Predicate<Object> isString = x -> x instanceof String;
        addPredicate("required", isString);

        Predicate<Object> notNullNotEmpty = x -> x != null && !x.equals("");
        addPredicate("notNullNotEmpty", notNullNotEmpty);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = x -> x.contains(str);
        addPredicate("contains", contains);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> minLength = x -> x.length() >= length;
        addPredicate("minLength", minLength);
        return this;
    }

}
