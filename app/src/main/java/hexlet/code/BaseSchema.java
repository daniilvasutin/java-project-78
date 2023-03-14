package hexlet.code;

public class BaseSchema {

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

    public boolean isValid(Object value) {



        return false;
    }
}
