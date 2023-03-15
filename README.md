### Hexlet tests and linter status:
[![Actions Status](https://github.com/daniilvasutin/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/daniilvasutin/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/e8d80e950be7e7d0a11e/maintainability)](https://codeclimate.com/github/daniilvasutin/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e8d80e950be7e7d0a11e/test_coverage)](https://codeclimate.com/github/daniilvasutin/java-project-78/test_coverage)

### Data Validator
#### This is a validator, that can check if String, Integer or Map is Valid.
#### You can initialize it by typing:
```
Validator v = new Validator;
```
___
#### To validate String:
```
StringSchema schema = v.string();

schema.isValid(null); // true
schema.isValid("true"); // true

schema.required(); // now the Strings will be valid only if they are String types
schemaisValid(null); // false

schemaminLength(3); // The String should have length 3 or more
schema.isValid("no"); // false
schema.isValid("yes"); // true

schema.contains("be").isValid("to be or not to be"); // check if given String is in isValid String
schema.contains("be").isValid("to be or not to be"); // true
schema.contains("ornot").isValid("to be or not to be"); // false
```
___
#### To validate Integer:
```
NumberSchema schema = v.number();

schema.isValid(null); // true
schema.isValid("0"); // false
schema.isValid(0); // true

schema.required(); // check if income Object is instanceOf Integer

schema.isValid(null); //false
schema.isValid(0); //true

schema.positive().isValid(0); // check if income number > 0
schema.positive().isValid(-1); // false
schema.positive().isValid(2); // true

schema.range(num1, num2); // check if given number is in range: num1 <= number <= num2
schema.range(1, 3);

schema.isValid(1); // true
schema.isValid(0); // false
```
___
#### To validate Map:
```
MapSchema = v.map();

schema.isValid(null); // check if given Object is instanceOf Map or null
schema.isValid(new HashMap<>()); // true

schema.required(); // check if income Object is instanceOf Map

schema.isValid(null); // false
schema.isValid(0); // false
Map<String, String> data = new HashMap<>();
schema.isValid(data); // true
data.put("map", "schema");
schema.isValid(data); // true

schema.sizeof(2); // check if given Map has given size

schema.isValid(data); // false
data.put("the answer", "is true");
schema.isValid(data); // true
```
___
#### If you've got a value in Map, that you need to check, we've got this validator:
```
MapSchema schema = v.map();

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Ilya");
human1.put("age", 25);

Map<String, BaseSchema> schemas = new HashMap<>(); 
// make sure that you're making the exact same key, that is given in Map, which you need to check.
// Current validator will work only if key instanceOf String.
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

schema.isValid(human1); // true
```