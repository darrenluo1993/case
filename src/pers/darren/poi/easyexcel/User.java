package pers.darren.poi.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;

import java.math.BigDecimal;
import java.util.Date;

import static com.alibaba.excel.enums.BooleanEnum.TRUE;
import static com.alibaba.excel.enums.poi.BorderStyleEnum.THIN;

@ColumnWidth(20)
@HeadRowHeight(22)
@ContentRowHeight(20)
@ContentFontStyle(fontName = "微软雅黑", fontHeightInPoints = 11)
@HeadFontStyle(fontName = "微软雅黑", fontHeightInPoints = 11, bold = TRUE)
@ContentStyle(borderTop = THIN, borderRight = THIN, borderBottom = THIN, borderLeft = THIN, horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER)
public class User {
    /**
     * 用户名
     */
    @ExcelProperty({"用户列表", "用户名"})
    private String userName;
    /**
     * 姓名
     */
    @ExcelProperty({"用户列表", "基本信息", "姓名"})
    private String fullName;
    /**
     * 性别
     */
    @ExcelProperty({"用户列表", "基本信息", "性别"})
    private String gender;
    /**
     * 年龄
     */
    @ExcelProperty({"用户列表", "基本信息", "年龄"})
    private Integer age;
    /**
     * 电话号码
     */
    @ExcelProperty({"用户列表", "私密信息", "电话号码"})
    private String phone;
    /**
     * 薪资
     */
    @ExcelProperty({"用户列表", "私密信息", "薪资"})
    private BigDecimal salary;
    /**
     * 地址
     */
    @ExcelProperty({"用户列表", "私密信息", "地址"})
    private String address;
    /**
     * 创建时间
     */
    @ExcelProperty({"用户列表", "创建时间"})
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    public User() {
    }

    public User(String userName, String fullName, String gender, Integer age, String phone, BigDecimal salary, String address, Date createdTime) {
        this.userName = userName;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.salary = salary;
        this.address = address;
        this.createdTime = createdTime;
    }

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
