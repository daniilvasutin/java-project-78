package hexlet.code;

import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestValid {

//    private static String file1;
//    private static String file2;
//
//    public static String getPath(String fileName) {
//        return Paths.get("src", "test", "resources", fileName)
//                .toFile().getAbsolutePath();
//    }
//
//    public final String getContent(String fileName) throws IOException {
//        Path filePath = Path.of(getPath(fileName));
//        return Files.readString(filePath);
//    }

//    @BeforeAll
//    public static void setUp() {
//        file1 = getPath("file1.json");
//        file2 = getPath("file2.json");
//    }

    @Test
    public void testDiffJsonFormatStylish() {

        Validator v = new Validator();

        StringSchema schemaString = v.string();

        assertThat(schemaString.isValid("")).isTrue(); // true
        // Пока на вызван метод required(), null считается валидным
        assertThat(schemaString.isValid(null)).isTrue(); // true

        schemaString.required();

        assertThat(schemaString.isValid("what does the fox say")).isTrue(); // true
        assertThat(schemaString.isValid("hexlet")).isTrue(); // true
        assertThat(schemaString.isValid(null)).isFalse(); // false
        assertThat(schemaString.isValid(5)).isFalse(); // false
        assertThat(schemaString.isValid("")).isFalse(); // false

        assertThat(schemaString.contains("wh").isValid("what does the fox say")).isTrue(); // true
        assertThat(schemaString.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(schemaString.contains("whatthe").isValid("what does the fox say")).isFalse(); // false

        assertThat(schemaString.isValid("what does the fox say")).isFalse(); // false


        NumberSchema schemaNumber = v.number();
        // Пока не вызван метод required(), null считается валидным
        assertThat(schemaNumber.isValid(null)).isTrue(); // true
        assertThat(schemaNumber.positive().isValid(null)).isTrue(); // true

        schemaNumber.required();

        assertThat(schemaNumber.isValid(null)).isFalse(); // false
        assertThat(schemaNumber.isValid(10)).isTrue(); // true
        assertThat(schemaNumber.isValid("5")).isFalse(); // false
        assertThat(schemaNumber.isValid(-10)).isFalse(); // false
//  Ноль - не положительное число
        assertThat(schemaNumber.isValid(0)).isFalse(); // false

        schemaNumber.range(5, 10);

        assertThat(schemaNumber.isValid(5)).isTrue(); // true
        assertThat(schemaNumber.isValid(10)).isTrue(); // true
        assertThat(schemaNumber.isValid(4)).isFalse(); // false
        assertThat(schemaNumber.isValid(11)).isFalse(); // false



        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(new HashMap())).isTrue(); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue(); // true


        MapSchema schemaMap = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schemaMap.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schemaMap.isValid(human1)).isTrue(); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schemaMap.isValid(human2)).isTrue(); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schemaMap.isValid(human3)).isFalse(); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schemaMap.isValid(human4)).isFalse(); // false
    }



    // уже false, так как добавлена ещё одна проверка contains("whatthe")
//
//        String format = "stylish";
//
//        String fileContent = getContent("TestStylish.txt");
//        String resultOfDiff = Differ.generate(file1, file2, format);
//
//        assertEquals(fileContent, Differ.generate(file1, file2));
//        assertEquals(fileContent, resultOfDiff);
//        assertThat(resultOfDiff).isEqualTo(fileContent);
//
//    @Test
//    public void testDiffJsonFormatPlain() throws Exception {
//
//        String format = "plain";
//
//        String fileContent = getContent("TestPlain.txt");
//        String resultOfDiff = Differ.generate(file1, file2, format);
//
//        assertThat(resultOfDiff).isEqualTo(fileContent);
//        // assertEquals(fileContent, resultOfDiff);
//    }
//
//    @Test
//    public void testDiffJsonFormatJson() throws Exception {
//
//        String format = "json";
//
//        String fileContent = getContent("TestJson.txt");
//        String resultOfDiff = Differ.generate(file1, file2, format);
//
//        assertEquals(fileContent, resultOfDiff);
//    }
//
//    @Test
//    public void testDiffYMLFormatPlain() throws Exception {
//
//        file1 = getPath("file1.yml");
//        file2 = getPath("file2.yml");
//        String format = "plain";
//
//        String fileContent = getContent("TestPlain.txt");
//        String resultOfDiff = Differ.generate(file1, file2, format);
//
//        assertEquals(fileContent, resultOfDiff);
//    }
}
