package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private Map<String, Predicate<Object>> validityChecks;

    public BaseSchema() {
        this.validityChecks = new LinkedHashMap<>();
        this.typeCheck();
    }

    abstract BaseSchema typeCheck();

    /**
     * @return Schema object with added check for not null
     */
    public BaseSchema required() {
        addValidityCheck("required", (Objects::nonNull));
        return  this;
    }

    public final void addValidityCheck(String checkName, Predicate<Object> method) {
        validityChecks.put(checkName, method);
    }

    public final boolean isValid(Object object) {
        return validityChecks.values()
                .stream()
                .allMatch(check -> check.test(object));
    }
}
