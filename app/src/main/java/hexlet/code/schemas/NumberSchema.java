package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema {
    private Map<String, Predicate<Object>> validityChecks;

    public NumberSchema() {
        this.validityChecks = new LinkedHashMap<>();
        this.typeCheck();
    }

    void typeCheck() {
        validityChecks.put("typeCheck", (content -> content instanceof Integer || content == null));
    }

    public void required() {
        validityChecks.put("required", (Objects::nonNull));
    }

    public void positive() {
        validityChecks.put("positive", (content -> content == null || (int) content > 0));
    }

    public void range(int lowLimit, int highLimit) {
        validityChecks.put("range", (content -> lowLimit <= (int) content && (int) content <= highLimit));
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
