package hexlet.code;

import java.util.Objects;

public abstract class BaseSchema {

    protected boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public void required() {
        this.required = true;
    }

    public boolean isRequired() {
        return this.required;
    }

    public abstract boolean isValid(Object value);
}
