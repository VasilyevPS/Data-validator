package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private Map<String, Predicate<Object>> validityChecks;

    public BaseSchema() {
        this.validityChecks = new LinkedHashMap<>();
        this.typeCheck();
    }

    abstract BaseSchema typeCheck();

    public void addValidityCheck(String checkName, Predicate<Object> method) {
        validityChecks.put(checkName, method);
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
