package hexlet.code;

public class StringSchema {

    private boolean required;
    private String contains;

    public StringSchema() {
        this.required = false;
        this.contains = "";
    }

    public boolean isValid(String str) {

        if (!this.required) {
            return true;
        } else if (str == null || str.length() == 0) {
            return false;
        } else if (str.length() > 0) {
            return true;
        }
        return false;
    }
    public boolean isValid(int number) {
        return false;
    }

    public void required() {
        this.required = true;
    }

    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

}
