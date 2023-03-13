package hexlet.code;

public class StringSchema extends BaseSchema{

    private String contains;

    public StringSchema() {
        super();
        this.contains = "";
    }

    public boolean isValid(Object o) {
        String str = String.valueOf(o);
        if (!super.isRequired()) {
            if (str.equals("null") || str.length() == 0) {
                return true;
            }
        }else if (!str.equals("null") && str.length() > 0 && str.contains(this.contains)) {
            return true;
        }
        return false;
    }
    public boolean isValid(int number) {
        return false;
    }

    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

}
