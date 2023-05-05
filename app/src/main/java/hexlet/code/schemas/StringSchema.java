package hexlet.code.schemas;


public final class StringSchema extends BaseSchema {

    @Override
    void typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof String || content == null));
    }

    public void required() {
        addValidityCheck("required", (content -> content != null && !content.equals("")));
    }

    public void minLength(int limit) {
        addValidityCheck("minLength", (content -> limit <= String.valueOf(content).length()));
    }

    public void contains(String text) {
        addValidityCheck("contains", (content -> String.valueOf(content).contains(text)));
    }
}
