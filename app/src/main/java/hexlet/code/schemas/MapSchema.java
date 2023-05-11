package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

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
        addValidityCheck("shape ", content -> {
                Map<?, ?> contentMap = (Map<?, ?>) content;
                for (Map.Entry<String, BaseSchema> entry: schemas.entrySet()) {
                    String parameterName = entry.getKey();
                    BaseSchema currentSchema = entry.getValue();
                    if (!currentSchema.isValid(contentMap.get(parameterName))) {
                        return false;
                    }
                }
                return true;
            }
        );
        return this;
    }
}
