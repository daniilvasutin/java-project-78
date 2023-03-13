package hexlet.code;

import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testDiffJsonFormatStylish() throws Exception {

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
    }
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
