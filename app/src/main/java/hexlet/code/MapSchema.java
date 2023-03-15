package hexlet.code;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> predicate = x -> x instanceof Map;
        addPredicate(predicate);
    }

    public void sizeof(int size) {
        Predicate<Map<String, Object>> predicate = x -> x.size() == size;
        addPredicate(predicate);
    }

    public MapSchema required() {
        setRequired(true);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        Predicate<Map<String, Object>> shape = map -> checkValue(schemas, map);
        addPredicate(shape);
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
