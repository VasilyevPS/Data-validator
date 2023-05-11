package hexlet.code.schemas;


public final class StringSchema extends BaseSchema {

    @Override
    StringSchema typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof String || content == null));
        return this;
    }

    public StringSchema required() {
        addValidityCheck("required", (content -> content != null && !content.equals("")));
        return this;
    }

    public StringSchema minLength(int limit) {
        addValidityCheck("minLength", (content -> limit <= String.valueOf(content).length()));
        return this;
    }

    public StringSchema contains(String text) {
        addValidityCheck("contains", (content -> String.valueOf(content).contains(text)));
        return this;
    }
}
