package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate> predicates = new ArrayList<>();

    public final void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public final boolean isValid(Object value) {

            for (var predicate: predicates) {
                if (!predicate.test(value)) {
                    return false;
                }
            }
        return true;
    }
}


