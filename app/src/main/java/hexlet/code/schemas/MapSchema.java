package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    void typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof Map || content == null));
    }

    public void required() {
        addValidityCheck("required", (content -> content != null));
    }

    public void sizeof(int size) {
        addValidityCheck("sizeof", (content -> ((Map<?, ?>) content).size()  == size));
    }
}
