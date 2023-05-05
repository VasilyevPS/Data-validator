package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {

    @Override
    void typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof Integer || content == null));
    }

    public void required() {
        addValidityCheck("required", (content -> content != null));
    }

    public void positive() {
        addValidityCheck("positive", (content -> content == null || (int) content > 0));
    }

    public void range(int lowLimit, int highLimit) {
        addValidityCheck("range", (content -> lowLimit <= (int) content && (int) content <= highLimit));
    }
}
