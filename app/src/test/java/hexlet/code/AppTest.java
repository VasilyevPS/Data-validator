package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

final class AppTest {

    @Test
    void stringSchemaTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(6)).isFalse();

        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.minLength(5);
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.contains("wh");
        assertThat(schema.isValid("what does the fox say")).isTrue();
        schema.contains("what");
        assertThat(schema.isValid("what does the fox say")).isTrue();
        schema.contains("whatthe");
        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    void numberSchemaTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("5")).isFalse();

        schema.positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(10)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();

        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void mapSchemaTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> testMap = new HashMap<>();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(testMap)).isTrue();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(6)).isFalse();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(testMap)).isTrue();
        testMap.put("key1", "value1");
        assertThat(schema.isValid(testMap)).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(testMap)).isFalse();
        testMap.put("key2", "value2");
        assertThat(schema.isValid(testMap)).isTrue();
        testMap.put("key3", "value3");
        assertThat(schema.isValid(testMap)).isFalse();
    }
}
