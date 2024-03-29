package pers.darren.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.Lists;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.core.JsonToken.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class JacksonCase {

    private static final String USER_JSON_STR = """
            {
                "ADDRESS": "Hunan ChangSha",
                "AGE": 18,
                "CREATED_TIME": "2022-07-15 08:56:01",
                "FULL_NAME": "Darren Luo",
                "GENDER": "Male",
                "PHONE": "0731-88888888",
                "TELEPHONE": "0731-99999999",
                "USER_NAME": "darrenluo1993",
                "SALARY": 10000,
                "UNKNOWN_JSON_PROP": "This is an unknown JSON property!",
                "IGNORED_CLASS_FIELD": "This is an ignored class field!"
            }
            """;

    private static final String USER_JSON_LIST_STR = """
            [
                {
                    "ADDRESS": "Hunan LouDi",
                    "AGE": 28,
                    "CREATED_TIME": "2022-07-15 15:01:01",
                    "FULL_NAME": "Zhang San",
                    "GENDER": "Male",
                    "PHONE": "0731-11111111",
                    "TELEPHONE": "0731-12341234",
                    "USER_NAME": "zhangsan",
                    "SALARY": 20000
                },
                {
                    "ADDRESS": "Hunan ChangSha",
                    "AGE": 30,
                    "CREATED_TIME": "2022-07-14 14:56:01",
                    "FULL_NAME": "Li Si",
                    "GENDER": "Female",
                    "PHONE": "0731-22222222",
                    "TELEPHONE": "0731-23452345",
                    "USER_NAME": "lisi",
                    "SALARY": 23000
                },
                {
                    "ADDRESS": "Hunan ZhuZhou",
                    "AGE": 26,
                    "CREATED_TIME": "2022-07-15 15:12:01",
                    "FULL_NAME": "Wang Wu",
                    "GENDER": "Female",
                    "PHONE": "0731-33333333",
                    "TELEPHONE": "0731-34563456",
                    "USER_NAME": "wangwu",
                    "SALARY": 15000
                }
            ]
            """;

    public static void main(String[] args) throws Exception {
        string2ObjectAndList();
        inputStream2ObjectAndList();
        file2ObjectAndList();
        string2MapAndList();
        string2JsonNode();
        objectAndList2Json();
        convertValue();
    }

    public static void string2ObjectAndList() throws JsonProcessingException {
        ////////////////////////////////To Object////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(USER_JSON_STR, User.class);
        System.out.println("String to Object, User>>>" + user);
        /////////////////////////////////To List/////////////////////////////////
        List<User> userList = mapper.readValue(USER_JSON_LIST_STR, new TypeReference<>() {
        });
        System.out.println("String to List, UserList>>>" + userList);
        System.out.println();
    }

    public static void inputStream2ObjectAndList() throws IOException {
        ////////////////////////////////To Object////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        URL jsonURL = JacksonCase.class.getResource("/user.json");
        System.out.println("URL>>>" + jsonURL);
        User user = mapper.readValue(jsonURL, User.class);
        System.out.println("URL to Object, User>>>" + user);
        user = mapper.readValue(JacksonCase.class.getResourceAsStream("/user.json"), User.class);
        System.out.println("InputStream to Object, User>>>" + user);
        user = mapper.readValue(new StringReader(USER_JSON_STR), User.class);
        System.out.println("Reader to Object, User>>>" + user);
        System.out.println();
        /////////////////////////////////To List/////////////////////////////////
        jsonURL = JacksonCase.class.getResource("/userlist.json");
        System.out.println("URL>>>" + jsonURL);
        List<User> userList = mapper.readValue(jsonURL, new TypeReference<>() {
        });
        System.out.println("URL to List, UserList>>>" + userList);
        userList = mapper.readValue(JacksonCase.class.getResourceAsStream("/userlist.json"), new TypeReference<>() {
        });
        System.out.println("InputStream to List, UserList>>>" + userList);
        userList = mapper.readValue(new StringReader(USER_JSON_LIST_STR), new TypeReference<>() {
        });
        System.out.println("Reader to List, UserList>>>" + userList);
        System.out.println();
    }

    public static void file2ObjectAndList() throws IOException {
        ////////////////////////////////To Object////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("resources/user.json");
        System.out.println("File path>>>" + jsonFile.getAbsolutePath());
        User user = mapper.readValue(jsonFile, User.class);
        System.out.println("File to Object, User>>>" + user);
        System.out.println();
        /////////////////////////////////To List/////////////////////////////////
        jsonFile = new File("resources/userlist.json");
        System.out.println("File path>>>" + jsonFile.getAbsolutePath());
        List<User> userList = mapper.readValue(jsonFile, new TypeReference<>() {
        });
        System.out.println("File to List, UserList>>>" + userList);
        System.out.println();
    }

    public static void byteArray2ObjectAndList() throws Exception {
        ////////////////////////////////To Object////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = USER_JSON_STR.getBytes(UTF_8);
        User user = mapper.readValue(bytes, User.class);
        System.out.println("byte[] to Object, User>>>" + user);
        /////////////////////////////////To List/////////////////////////////////
        bytes = USER_JSON_LIST_STR.getBytes(UTF_8);
        List<User> userList = mapper.readValue(bytes, new TypeReference<>() {
        });
        System.out.println("byte[] to List, UserList>>>" + userList);
    }

    public static void jsonArray2ObjectArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User[] users = mapper.readValue(USER_JSON_LIST_STR, User[].class);
        System.out.println("String to Object Array, User[]>>>" + Arrays.toString(users));
        users = mapper.readValue(new File("resources/userlist.json"), User[].class);
        System.out.println("File to Object Array, User[]>>>" + Arrays.toString(users));
        users = mapper.readValue(JacksonCase.class.getResource("/userlist.json"), User[].class);
        System.out.println("URL to Object Array, User[]>>>" + Arrays.toString(users));
        users = mapper.readValue(JacksonCase.class.getResourceAsStream("/userlist.json"), User[].class);
        System.out.println("InputStream to Object Array, User[]>>>" + Arrays.toString(users));
        users = mapper.readValue(new StringReader(USER_JSON_LIST_STR), User[].class);
        System.out.println("Reader to Object Array, User[]>>>" + Arrays.toString(users));
        users = mapper.readValue(USER_JSON_LIST_STR.getBytes(UTF_8), User[].class);
        System.out.println("byte[] to Object Array, User[]>>>" + Arrays.toString(users));
        DataInput dataInput = new DataInputStream(JacksonCase.class.getResourceAsStream("/userlist.json"));
        users = mapper.readValue(dataInput, User[].class);
        System.out.println("DataInput to Object Array, User[]>>>" + Arrays.toString(users));
    }

    public static void string2MapAndList() throws JsonProcessingException {
        /////////////////////////////////To Map//////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        Map<String, ?> map = mapper.readValue(USER_JSON_STR, new TypeReference<>() {
        });
        System.out.println("String to Map, Map>>>" + map);
        /////////////////////////////////To List/////////////////////////////////
        List<Map<String, ?>> mapList = mapper.readValue(USER_JSON_LIST_STR, new TypeReference<>() {
        });
        System.out.println("String to MapList, MapList>>>" + mapList);
        System.out.println();
    }

    public static void objectAndList2Json() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(USER_JSON_STR, User.class);
        List<User> userList = mapper.readValue(USER_JSON_LIST_STR, new TypeReference<>() {
        });
        // writeValueAsString(Object value)
        String userJsonStr = mapper.writeValueAsString(user);
        System.out.println("Object to String, UserJsonStr>>>" + userJsonStr);
        String userListJsonStr = mapper.writeValueAsString(userList);
        System.out.println("List to String, UserListJsonStr>>>" + userListJsonStr);
        System.out.println();
        // writeValue(Writer writer, Object value)
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, user);
        System.out.println("Object to Writer>>>" + writer);
        writer = new StringWriter();
        mapper.writeValue(writer, userList);
        System.out.println("List to Writer>>>" + writer);
        System.out.println();
        // writeValue(File resultFile, Object value)
        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("/home/darren/Temporary/json/user.json"), user);
        objectWriter.writeValue(new File("/home/darren/Temporary/json/userlist.json"), userList);
        // writeValue(OutputStream out, Object value)
        // writeValueAsBytes(Object value)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mapper.writeValue(baos, user);
        byte[] userJsonByteArr = baos.toByteArray();
        System.out.println("Object to ByteArrayOutputStream>>>" + Arrays.toString(userJsonByteArr));
        userJsonByteArr = mapper.writeValueAsBytes(user);
        System.out.println("Object to ByteArray>>>" + Arrays.toString(userJsonByteArr));
        System.out.println();
        baos.reset();
        mapper.writeValue(baos, userList);
        byte[] userListJsonByteArr = baos.toByteArray();
        System.out.println("List to ByteArrayOutputStream>>>" + Arrays.toString(userListJsonByteArr));
        userListJsonByteArr = mapper.writeValueAsBytes(userList);
        System.out.println("List to ByteArray>>>" + Arrays.toString(userListJsonByteArr));
        // writeValue(FileOutputStream out, Object value)
        FileOutputStream fos = new FileOutputStream("/home/darren/Temporary/json/user-fos.json");
        objectWriter.writeValue(fos, user);
        fos = new FileOutputStream("/home/darren/Temporary/json/userlist-fos.json");
        objectWriter.writeValue(fos, userList);
    }

    public static void string2JsonNode() throws JsonProcessingException {
        String json = """
                {
                    "realName": "ZhangSan",
                    "gender": "Male",
                    "age": 43,
                    "spouse": {
                        "realName": "LiSi",
                        "gender": "Female",
                        "age": 40,
                        "company": "Hunan Link-us",
                        "hobby": [
                            "swimming",
                            "fitness",
                            "listen to music",
                            "sing"
                        ]
                    },
                    "children": [
                        {
                            "realName": "ZhangSi",
                            "age": 18
                        },
                        {
                            "realName": "ZhangWu",
                            "age": 17
                        }
                    ],
                    "contact": [
                        {
                            "type": "telephone",
                            "value": "0731-88888888"
                        },
                        {
                            "type": "mobilePhone",
                            "value": "15112341234"
                        },
                        {
                            "type": "email",
                            "value": "zhangsan@163.com"
                        }
                    ]
                }
                """;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json); // Or mapper.readValue(json, JsonNode.class);
        System.out.println("node.get(\"spouse\").isArray()>>>" + node.get("spouse").isArray());
        System.out.println("node.get(\"children\").isArray()>>>" + node.get("children").isArray());
        System.out.println("node.get(\"realName\").isValueNode()>>>" + node.get("realName").isValueNode());
        System.out.println("node.get(\"age\").isInt()>>>" + node.get("age").isInt());
        // asInt方法直接调用intValue方法
        System.out.println("node.get(\"age\").asInt()>>>" + node.get("age").asInt());
        System.out.println("node.get(\"age\").intValue()>>>" + node.get("age").intValue());
        System.out.println("node.get(\"gender\").asText()>>>" + node.get("gender").asText());
        System.out.println("node.get(\"gender\").textValue()>>>" + node.get("gender").textValue());
        System.out.println("node.get(\"company\")>>>" + node.get("company"));
        System.out.println("node.at(\"/spouse/company\").textValue()>>>" + node.at("/spouse/company").textValue());
        System.out.println("node.get(\"spouse\").get(\"company\").textValue()>>>" + node.get("spouse").get("company").textValue());
        System.out.println("node.findValue(\"company\").textValue()>>>" + node.findValue("company").textValue());
        System.out.println("node.has(\"contact\")>>>" + node.has("contact"));
        System.out.println("node.get(\"contact\").isValueNode()>>>" + node.get("contact").isValueNode());
        System.out.println("node.get(\"contact\").getNodeType().name()>>>" + node.get("contact").getNodeType().name());
        System.out.println("node.get(\"contact\").get(1).get(\"value\").textValue()>>>" + node.get("contact").get(1).get("value").textValue());
        System.out.println("node.get(\"spouse\").get(\"hobby\").findParent(\"spouse\")>>>" + node.get("spouse").get("hobby").findParent("spouse"));
        System.out.println("node.findValues(\"realName\").forEach(System.out::println)>>>");
        node.findValues("realName").forEach(System.out::println);
        System.out.println("node.findValuesAsText(\"realName\").forEach(System.out::println)>>>");
        node.findValuesAsText("realName").forEach(System.out::println);
        System.out.println();
    }

    public static void convertValue() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("======================com.fasterxml.jackson.databind.ObjectMapper.convertValue(entity, User.class)======================");
        User user1 = mapper.readValue(USER_JSON_STR, User.class);
        User user2 = mapper.convertValue(user1, User.class);
        System.out.println("user1==user2>>>" + (user1 == user2));
        System.out.println("user1>>>" + user1);
        System.out.println("user2>>>" + user2);
        System.out.println("======================com.fasterxml.jackson.databind.ObjectMapper.convertValue(base64, byte[].class)======================");
        String before = user1.toJSON();
        System.out.println("base64Before>>>" + before);
        byte[] bytes = before.getBytes();
        System.out.println("beforeToBytes>>>" + Arrays.toString(bytes));
        String base64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println("base64>>>" + base64);
        // 解码Base64字符串
        bytes = mapper.convertValue(base64, byte[].class);
        System.out.println("base64ToBytes>>>" + Arrays.toString(bytes));
        System.out.println("base64BytesToString>>>" + new String(bytes));
        System.out.println("base64ToString>>>" + mapper.convertValue(base64, String.class));
        System.out.println("======================com.fasterxml.jackson.databind.ObjectMapper.convertValue(List<Integer>, int[].class)======================");
        List<Integer> integerList = Lists.newArrayList(1, 2, 3);
        System.out.println("List<Integer> values>>>" + integerList);
        int[] ints = mapper.convertValue(integerList, int[].class);
        System.out.println("List<Integer> to int[]>>>" + Arrays.toString(ints));
        Integer[] integers = mapper.convertValue(integerList, Integer[].class);
        System.out.println("List<Integer> to Integer[]>>>" + Arrays.toString(integers));
        System.out.println("======================com.fasterxml.jackson.databind.ObjectMapper.convertValue(entity, Map.class)======================");
        Map<String, Object> userMap = mapper.convertValue(user1, Map.class);
        System.out.println("User to Map<String, Object>>>>" + userMap);
        System.out.println("======================com.fasterxml.jackson.databind.ObjectMapper.convertValue(Map, entity.class)======================");
        User user3 = mapper.convertValue(userMap, User.class);
        System.out.println("Map<String, Object> to User>>>" + user3);
    }

    public static void jsonGeneratorParser() throws IOException {
        JsonFactory factory = new JsonFactory();
        // 生成JSON
        System.out.println("=================================生成JSON=================================");
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);
        generator.useDefaultPrettyPrinter();
        generator.writeStartObject();
        generator.writeStringField("userName", "username1");
        generator.writeStringField("fullName", "Full Name 1");
        generator.writeStringField("gender", "Male");
        generator.writeNumberField("age", 22);
        generator.writeNumberField("weight", 60.5);
        generator.writeNumberField("salary", new BigDecimal("15000"));
        generator.writeStringField("company", "Hunan Link-us");
        generator.writeArrayFieldStart("area");
        generator.writeNumber(141.5);
        generator.writeNumber(73.1);
        generator.writeNumber(129.9);
        generator.writeEndArray();
        generator.writeObjectFieldStart("spouse");
        generator.writeStringField("userName", "username2");
        generator.writeStringField("fullName", "Full Name 2");
        generator.writeStringField("gender", "Female");
        generator.writeNumberField("age", 20);
        generator.writeNumberField("weight", 45.7);
        generator.writeArrayFieldStart("bwh");
        generator.writeNumber(100);
        generator.writeNumber(75);
        generator.writeNumber(80);
        generator.writeEndArray();
        generator.writeNumberField("salary", new BigDecimal("10000"));
        generator.writeStringField("company", "Hunan JiaJie Group");
        generator.writeArrayFieldStart("hobby");
        generator.writeString("swimming");
        generator.writeString("sing");
        generator.writeString("fitness");
        generator.writeEndArray();
        generator.writeEndObject();
        generator.writeArrayFieldStart("contact");
        generator.writeStartObject();
        generator.writeStringField("type", "telephone");
        generator.writeStringField("value", "0731-88888888");
        generator.writeEndObject();
        generator.writeStartObject();
        generator.writeStringField("type", "mobilePhone");
        generator.writeStringField("value", "15112344321");
        generator.writeEndObject();
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();
        String userJson = writer.toString();
        System.out.println(userJson);
        // 解析JSON
        System.out.println("=================================解析JSON=================================");
        JsonParser parser = factory.createParser(userJson);
        while (!parser.isClosed()) {
            JsonToken token = parser.nextToken();
            // 如果token类型为字段，使用getCurrentName获取名称
            if (token == FIELD_NAME) {
                System.out.print(parser.getCurrentName() + "=");
                token = parser.nextToken();
                if (token == VALUE_STRING) { // 如果token类型为字符串值，使用getValueAsString获取值
                    System.out.println(parser.getValueAsString());
                } else if (token == VALUE_NUMBER_INT) { // 如果token类型为整型值，使用getValueAsInt获取值
                    System.out.println(parser.getValueAsInt());
                } else if (token == VALUE_NUMBER_FLOAT) { // 如果token类型为浮点型值，使用getValueAsDouble获取值
                    System.out.println(parser.getValueAsDouble());
                } else if (token == START_ARRAY) {
                    System.out.println("[]");
                } else if (token == START_OBJECT) {
                    System.out.println("{}");
                } else {
                    System.out.println();
                }
            } else if (token == VALUE_STRING) {
                System.out.println(parser.getValueAsString());
            } else if (token == VALUE_NUMBER_INT) {
                System.out.println(parser.getValueAsInt());
            } else if (token == VALUE_NUMBER_FLOAT) {
                System.out.println(parser.getValueAsDouble());
            }
        }
    }

    public static void valueToTreeAndTreeToValue() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(USER_JSON_STR, User.class);
        List<User> userList = mapper.readValue(USER_JSON_LIST_STR, new TypeReference<>() {
        });
        JsonNode userTree = mapper.valueToTree(user);
        System.out.println("Object valueToTree, userTree>>>" + userTree);
        System.out.println("userTree.get(\"USER_NAME\").textValue()>>>" + userTree.get("USER_NAME").textValue());
        JsonNode userListTree = mapper.valueToTree(userList);
        System.out.println("List valueToTree, userListTree>>>" + userListTree);
        user = mapper.treeToValue(userTree, User.class);
        System.out.println("Object treeToValue, User>>>" + user);
        // userListTree无法通过treeToValue方法转回成userList，只能转成List<Map>
        List list = mapper.treeToValue(userListTree, List.class);
        System.out.println("List treeToValue, List>>>" + list);
    }
}

@JsonInclude(NON_EMPTY) // 仅序列化值非空和非空字符串的字段
@JsonIgnoreProperties(ignoreUnknown = true) // 反序列化时忽略类中没有对应字段或Setter的JSON属性
class User {
    /**
     * 用户名
     */
    @JsonProperty("USER_NAME")
    private String userName;
    /**
     * 姓名
     */
    @JsonProperty("FULL_NAME")
    private String fullName;
    /**
     * 性别
     */
    @JsonProperty("GENDER")
    private String gender;
    /**
     * 年龄
     */
    @JsonProperty("AGE")
    private Integer age;
    /**
     * 电话号码
     */
    @JsonProperty("PHONE")
    @JsonAlias("TELEPHONE")
    private String phone;
    /**
     * 薪资
     */
    @JsonProperty("SALARY")
    private BigDecimal salary;
    /**
     * 地址
     */
    @JsonProperty("ADDRESS")
    private String address;
    /**
     * 创建时间
     */
    @JsonProperty("CREATED_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonProperty("VALUE_IS_EMPTY") // 受@JsonInclude(NON_EMPTY)限制，此空值字段不会被序列化
    private String valueIsEmpty;

    @JsonIgnore // 表示此字段在序列化和反序列化的时候都将被忽略
    @JsonProperty("IGNORED_CLASS_FIELD")
    private String ignoredClassField;

    @JsonRawValue
    @JsonProperty("RAW_VALUE")
    private String rawValue = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getValueIsEmpty() {
        return valueIsEmpty;
    }

    public void setValueIsEmpty(String valueIsEmpty) {
        this.valueIsEmpty = valueIsEmpty;
    }

    public String getIgnoredClassField() {
        return ignoredClassField;
    }

    public void setIgnoredClassField(String ignoredClassField) {
        this.ignoredClassField = ignoredClassField;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    @JsonProperty("BASIC_INFO")
    public String getBasicInfo() {
        return "Full Name:" + this.fullName + ", User Name:" + this.userName + ", Telephone:" + this.phone;
    }

    @JsonValue(false)
    public String toJSON() {
        return "{\"userName\":\"" + userName + "\", \"fullName\":\"" + fullName + "\", \"gender\":\"" + gender + "\", \"age\":" + age + ", \"phone\":\"" + phone + "\", \"salary\":" + salary + ", \"address\":\"" + address + "\", \"createdTime\":\"" + createdTime + "\"}";
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", fullName='" + fullName + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", phone='" + phone + '\'' + ", salary=" + salary + ", address='" + address + '\'' + ", createdTime=" + createdTime + ", valueIsEmpty='" + valueIsEmpty + '\'' + ", ignoredClassField='" + ignoredClassField + '\'' + '}';
    }
}