package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    MapSchema typeCheck() {
        addValidityCheck("typeCheck", (content -> content instanceof Map || content == null));
        return this;
    }

    public MapSchema required() {
        addValidityCheck("required", (content -> content != null));
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidityCheck("sizeof", (content -> ((Map<?, ?>) content).size()  == size));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        return this;
    }
}
