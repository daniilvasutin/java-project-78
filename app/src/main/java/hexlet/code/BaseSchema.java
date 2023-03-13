package hexlet.code;

public abstract class BaseSchema {

    protected boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired() {
        return this.required;
    }

    public abstract boolean isValid(Object value);
}
