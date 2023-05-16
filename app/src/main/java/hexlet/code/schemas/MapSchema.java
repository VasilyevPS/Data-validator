package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    @Override
    MapSchema typeCheck() {
        addValidityCheck("typeCheck", (map -> map instanceof Map || map == null));
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidityCheck("sizeof", (map -> map == null || ((Map<?, ?>) map).size()  == size));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addValidityCheck("shape ", map -> {
                Map<?, ?> contentMap = (Map<?, ?>) map;
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
