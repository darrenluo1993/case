package pers.darren.jackson;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
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