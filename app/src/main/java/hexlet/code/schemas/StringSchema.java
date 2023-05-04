package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    private Map<String, Predicate<Object>> validityChecks;

    public StringSchema() {
        this.validityChecks = new LinkedHashMap<>();
        this.typeCheck();
    }

    void typeCheck() {
        validityChecks.put("typeCheck", (content -> content instanceof String || content == null));
    }

    public void required() {
        validityChecks.put("required", (content -> content != null && !content.equals("")));
    }

    public void minLength(int limit) {
        validityChecks.put("minLength", (content -> limit <= String.valueOf(content).length()));
    }

    public void contains(String text) {
        validityChecks.put("contains", (content -> String.valueOf(content).contains(text)));
    }

    public boolean isValid(Object content) {
        for (String check: validityChecks.keySet()) {
            if  (!validityChecks.get(check).test(content)) {
                return false;
            }
        }
        return true;
    }
}
