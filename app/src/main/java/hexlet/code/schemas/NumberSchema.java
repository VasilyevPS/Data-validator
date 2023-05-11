package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {

    @Override
    NumberSchema typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof Integer || content == null));
        return  this;
    }

    public NumberSchema required() {
        addValidityCheck("required", (content -> content != null));
        return  this;
    }

    public NumberSchema positive() {
        addValidityCheck("positive", (content -> content == null || (int) content > 0));
        return  this;
    }

    public NumberSchema range(int lowLimit, int highLimit) {
        addValidityCheck("range", (content -> lowLimit <= (int) content && (int) content <= highLimit));
        return  this;
    }
}
