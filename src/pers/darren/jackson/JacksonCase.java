package pers.darren.jackson;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                "SALARY": 10000
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
    }

    private static void string2ObjectAndList() throws JsonProcessingException {
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

    private static void inputStream2ObjectAndList() throws IOException {
        ////////////////////////////////To Object////////////////////////////////
        ObjectMapper mapper = new ObjectMapper();
        URL jsonURL = JacksonCase.class.getResource("/user.json");
        System.out.println("URL>>>" + jsonURL);
        User user = mapper.readValue(jsonURL, User.class);
        System.out.println("URL to Object, User>>>" + user);
        user = mapper.readValue(JacksonCase.class.getResourceAsStream("/user.json"), User.class);
        System.out.println("InputStream to Object, User>>>" + user);
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
        System.out.println();
    }

    private static void file2ObjectAndList() throws IOException {
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

    private static void string2MapAndList() throws JsonProcessingException {
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

    private static void objectAndList2Json() throws IOException {
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
        fos.close();
        fos = new FileOutputStream("/home/darren/Temporary/json/userlist-fos.json");
        objectWriter.writeValue(fos, userList);
        fos.close();
    }

    private static void string2JsonNode() throws JsonProcessingException {
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
}

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

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", fullName='" + fullName + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", phone='" + phone + '\'' + ", salary=" + salary + ", address='" + address + '\'' + ", createdTime=" + createdTime + '}';
    }
}