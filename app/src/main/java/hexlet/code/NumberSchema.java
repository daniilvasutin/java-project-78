package hexlet.code;

public class NumberSchema extends BaseSchema{
    private boolean positive;
    private boolean setRange;
    private int rangeMin;
    private int rangeMax;

    public NumberSchema() {
        this.setRange = false;
        this.positive = false;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        this.setRange = true;
        return this;
    }

    public boolean isPositive() {
        return positive;
    }

    public boolean isSetRange() {
        return setRange;
    }

    public boolean isValid(Object o) {
        if (!super.isRequired() && o == null) {
            return true;
        }
        if (o instanceof Integer) {
            Integer value = (Integer) o;
            if (isPositive() && value > 0) {
                if (!isSetRange()) {
                    return true;
                } else if (value >= rangeMin && value <= rangeMax) {
                    return true;
                }
            }
        }
        return false;
    }




}
