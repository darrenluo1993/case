package pers.darren.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class FastJsonCase {

    public static void main(String[] args) {
        var user = new User();
        user.setUserName("darrenluo1993");
        user.setFullName("Darren Luo");
        user.setGender("Male");
        user.setAge(18);
        user.setPhone("0731-88888888");
        user.setSalary(BigDecimal.valueOf(1000.5));
        user.setAddress("Hunan ChangSha");
        user.setCreatedTime(new Date());
        System.out.println("user>>>" + user);
        var userJsonStr = JSON.toJSONString(user);
        System.out.println("userJsonStr>>>" + userJsonStr);
        user = JSON.parseObject(userJsonStr, User.class);
        System.out.println("JSON.parseObject(String,Class)>>>" + user);
        JSONObject userJson = JSON.parseObject(userJsonStr);
        System.out.println("userJson>>>" + userJson);
        user = JSON.toJavaObject(userJson, User.class);
        System.out.println("JSON.toJavaObject(Object,Class)>>>" + user);
        user = userJson.toJavaObject(User.class);
        System.out.println("JSONObject.toJavaObject(Class)>>>" + user);
    }
}

class User {
    /**
     * 用户名
     */
    @JSONField(name = "USER_NAME")
    private String userName;
    /**
     * 姓名
     */
    @JSONField(name = "FULL_NAME")
    private String fullName;
    /**
     * 性别
     */
    @JSONField(name = "GENDER")
    private String gender;
    /**
     * 年龄
     */
    @JSONField(name = "AGE")
    private Integer age;
    /**
     * 电话号码
     */
    @JSONField(name = "PHONE")
    private String phone;
    /**
     * 薪资
     */
    @JSONField(name = "SALARY", serialize = false)
    private BigDecimal salary;
    /**
     * 地址
     */
    @JSONField(name = "ADDRESS")
    private String address;
    /**
     * 创建时间
     */
    @JSONField(name = "CREATED_TIME", format = "yyyy-MM-dd HH:mm:ss", deserialize = false)
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