package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestValid {

    @Test
    public void testString() {

        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("java")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(5)).isFalse();

        schema.minLength(4);
        assertThat(schema.isValid("Wha")).isFalse();
        assertThat(schema.isValid("What")).isTrue();

        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("the fox").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    public void testNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        schema.range(5, 10);
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    public void testMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();

        Map<String, String> data = new HashMap<>();

        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    public void testMapDataValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
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
    }
}
