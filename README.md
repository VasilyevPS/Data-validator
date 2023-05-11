[![Actions Status](https://github.com/VasilyevPS/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/VasilyevPS/java-project-78/actions/workflows/hexlet-check.yml)
[![JavaCI](https://github.com/VasilyevPS/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/VasilyevPS/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/903b982d80978625d13e/maintainability)](https://codeclimate.com/github/VasilyevPS/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/903b982d80978625d13e/test_coverage)](https://codeclimate.com/github/VasilyevPS/java-project-78/test_coverage)

# Data validator
## Description
Data validator is a library which helps to check the correctness of data.  
It can be used for strings, integer numbers and maps. 

## How to use
Create Validator object and define validation scheme by using `string()`, `number()` or `map()`.  
Example:
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();
StringSchema schema = v.string();
```

There are methods for each data type to add some limitations. After the scheme is customized, `isValid()` method shows if the data is correct.

### String validation
* `required()` — string should be non-empty (does not equal `null` or `""`)
* `minLength()` — string's length should be equal or more than specified number
* `contains()` — string should contain specified substring

Example:
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();
StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
```

### Number validation
* `required()` — number should be not `null`
* `positive()` — number should be positive
* `range()` — number should fit in specified range 

Example:
```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid("5"); // false
schema.isValid(10) // true

schema.isValid(-10); // false
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

### Map validation
* `required()` — value should be not `null`, only map fits to this limitation
* `sizeof()` — map should have the exact amount of key-value pairs

Example:
```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

### Nested data validation
* `shape()` — allows to add limitations for values inside maps

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```