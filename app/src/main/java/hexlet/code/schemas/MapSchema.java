package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public void sizeof(int size) {
        Predicate<Map<String, Object>> predicate = x -> x.size() == size;
        addPredicate("sizeof", predicate);
    }

    public MapSchema required() {
        Predicate<Object> predicate = x -> x instanceof Map;
        addPredicate("required", predicate);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        Predicate<Map<String, Object>> shape = map -> checkValue(schemas, map);
        addPredicate("shape", shape);
    }

    private boolean checkValue(Map<String, BaseSchema> schemas, Map<String, Object> map) {
        for (var item : schemas.entrySet()) {
            var schema = item.getValue();
            var valueFromMap = map.get(item.getKey());
            if (!schema.isValid(valueFromMap)) {
                return false;
            }
        }
        return true;
    }
}
