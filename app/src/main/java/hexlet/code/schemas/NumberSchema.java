package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {

    @Override
    NumberSchema typeCheck() {
        addValidityCheck("typeCheck", (n -> n instanceof Integer || n == null));
        return  this;
    }

    public NumberSchema positive() {
        addValidityCheck("positive", (n -> n == null || (int) n > 0));
        return  this;
    }

    public NumberSchema range(int lowLimit, int highLimit) {
        addValidityCheck("range", (n -> n == null || lowLimit <= (int) n && (int) n <= highLimit));
        return  this;
    }
}
