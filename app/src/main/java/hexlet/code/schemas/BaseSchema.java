package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate> predicates = new HashMap<>();

    public final void addPredicate(String predicateName, Predicate predicate) {
        predicates.put(predicateName, predicate);
    }

    public final boolean isValid(Object value) {

        for (var predicate: predicates.entrySet()) {
            if (!predicate.getValue().test(value)) {
                return false;
            }
        }
        return true;
    }
}


