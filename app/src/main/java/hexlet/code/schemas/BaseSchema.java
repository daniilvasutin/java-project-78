package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private boolean isRequired;
    private final List<Predicate> predicates;

    public final void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public final void setRequired(boolean required) {
        this.isRequired = required;
    }

    public BaseSchema() {
        this.isRequired = false;
        predicates = new ArrayList<>();
    }

    public final boolean isRequired() {
        return this.isRequired;
    }

    public final boolean isValid(Object value) {

        if (!isRequired() && isNullOrEmpty(value)) {
            return true;
        } else if (isRequired() && isNullOrEmpty(value)) {
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

    public final boolean isNullOrEmpty(Object value) {
        return value == null || value.equals("");
    }
}


