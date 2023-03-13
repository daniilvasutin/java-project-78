package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema{

    private Map mapSchema;
    private int sizeOf;
    private boolean setSizeOf;

    public MapSchema() {
        this.mapSchema = null;
        this.setSizeOf = false;
        this.sizeOf = 0;
    }

    public boolean isSizeOf() {
        return setSizeOf;
    }

    @Override
    public boolean isValid(Object o) {
        if (!super.isRequired() && o == null) {
                return true;
        }
        if (o instanceof HashMap<?,?>) {
            if (isSizeOf() && ((HashMap<?, ?>) o).size() == sizeOf) {
                return true;
            } else if (!isSizeOf()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.convertValue(o, new TypeReference<>() {});
                //boolean flag = false;
                if (map.size() > 0 && mapSchema != null) {
                    for (var item : map.entrySet()) {
                        BaseSchema schema= (BaseSchema) mapSchema.get(item.getKey());
                        if (!schema.isValid(item.getValue())) {
                            return false;
                            //flag = true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void sizeof(int size) {
        this.sizeOf = size;
        this.setSizeOf = true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.mapSchema = schemas;
    }


}
