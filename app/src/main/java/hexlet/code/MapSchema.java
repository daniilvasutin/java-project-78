package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema{

    private Map map;
    private int sizeOf;
    private boolean setSizeOf;

    public MapSchema() {
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
                return true;
            }
        }
        return false;
    }

    public void sizeof(int size) {
        this.sizeOf = size;
        this.setSizeOf = true;
    }
}
