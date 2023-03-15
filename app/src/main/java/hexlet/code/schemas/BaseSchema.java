package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected boolean required;
    private final List<Predicate> predicates;

    public void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public BaseSchema() {
        this.required = false;
        predicates = new ArrayList<>();
    }

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isValid(Object value) {

        if (!isRequired() && (value == null || value.equals(""))) {
            return true;
        } else if (isRequired() && (value == null || value.equals(""))) {
            return false;
        } else {
            for (var predicate: predicates) {
                if (!predicate.test(value)) {
                    return false;
                }
            }
        }
        return true;
    }
}


