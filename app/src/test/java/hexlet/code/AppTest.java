package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

    @Test
    void mapSchemaShapeTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schema.isValid(human4)).isFalse();

        schemas.put("name", v.string().required().contains("Ma"));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        assertThat(schema.isValid(human1)).isFalse();
        assertThat(schema.isValid(human2)).isTrue();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive().required());
        schema.shape(schemas);

        assertThat(schema.isValid(human1)).isTrue();
        assertThat(schema.isValid(human2)).isFalse();

    }
}
