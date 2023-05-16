package hexlet.code.schemas;


public final class StringSchema extends BaseSchema {

    @Override
    StringSchema typeCheck() {
        addValidityCheck("typeCheck", (s -> s instanceof String || s == null));
        return this;
    }

    @Override
    public StringSchema required() {
        addValidityCheck("required", (s -> s != null && !s.equals("")));
        return this;
    }

    public StringSchema minLength(int limit) {
        addValidityCheck("minLength", (s -> s == null || limit <= ((String) s).length()));
        return this;
    }

    public StringSchema contains(String text) {
        addValidityCheck("contains", (s -> s == null || ((String) s).contains(text)));
        return this;
    }
}
