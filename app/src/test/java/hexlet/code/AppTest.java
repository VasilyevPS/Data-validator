package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

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
}
