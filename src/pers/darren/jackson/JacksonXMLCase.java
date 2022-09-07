package pers.darren.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public class JacksonXMLCase {

    private static final String EMPLOYEE_XML = """
            <Employee jobNum="00000001">
                <UserName>darrenluo1993</UserName>
                <FullName>Darren Luo</FullName>
                <Gender>Male</Gender>
                <Age>18</Age>
                <Phone>15188888888</Phone>
                <Salary>10000</Salary>
                <Address>Hunan ChangSha</Address>
                <CreatedTime>2022-09-05 07:45:46</CreatedTime>
                <UnknownXmlEle>This is an unknown XML element!</UnknownXmlEle>
                <IgnoredClassField>This is an ignored class field!</IgnoredClassField>
                <SubordinateList>
                    <Subordinate jobNum="00000002">
                        <UserName>UserName1</UserName>
                        <FullName>FullName1</FullName>
                        <Gender>Male</Gender>
                        <Age>18</Age>
                        <Phone>13212344321</Phone>
                        <Salary>9000</Salary>
                        <Address>Hunan ZhuZhou</Address>
                        <CreatedTime>2022-09-05 07:45:46</CreatedTime>
                        <UnknownXmlEle>This is an unknown XML element!</UnknownXmlEle>
                        <IgnoredClassField>This is an ignored class field!</IgnoredClassField>
                    </Subordinate>
                    <Subordinate jobNum="00000003">
                        <UserName>UserName2</UserName>
                        <FullName>FullName2</FullName>
                        <Gender>Female</Gender>
                        <Age>17</Age>
                        <Phone>13445677654</Phone>
                        <Salary>9500</Salary>
                        <Address>Hunan YueYang</Address>
                        <CreatedTime>2022-09-05 07:45:46</CreatedTime>
                        <UnknownXmlEle>This is an unknown XML element!</UnknownXmlEle>
                        <IgnoredClassField>This is an ignored class field!</IgnoredClassField>
                    </Subordinate>
                </SubordinateList>
            </Employee>
            """;

    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        employee.setXmlText("employee");
        employee.setXmlCData("https://wwww.employee.com");
        employee.setJobNum("00000001");
        employee.setUserName("darrenluo1993");
        employee.setFullName("Darren Luo");
        employee.setGender("Male");
        employee.setAge(18);
        employee.setPhone("15188888888");
        employee.setAddress("Hunan ChangSha");
        employee.setSalary(new BigDecimal("10000"));
        employee.setCreatedTime(new Date());

        Employee subordinate1 = new Employee();
        subordinate1.setXmlText("subordinate1");
        subordinate1.setXmlCData("https://wwww.subordinate1.com");
        subordinate1.setJobNum("00000002");
        subordinate1.setUserName("UserName1");
        subordinate1.setFullName("FullName1");
        subordinate1.setGender("Male");
        subordinate1.setAge(18);
        subordinate1.setPhone("13212344321");
        subordinate1.setAddress("Hunan ZhuZhou");
        subordinate1.setSalary(new BigDecimal("9000"));
        subordinate1.setCreatedTime(new Date());
        employee.getSubordinateList().add(subordinate1);

        Employee subordinate2 = new Employee();
        subordinate2.setXmlText("subordinate2");
        subordinate2.setXmlCData("https://wwww.subordinate2.com");
        subordinate2.setJobNum("00000003");
        subordinate2.setUserName("UserName2");
        subordinate2.setFullName("FullName2");
        subordinate2.setGender("Female");
        subordinate2.setAge(17);
        subordinate2.setPhone("13445677654");
        subordinate2.setAddress("Hunan YueYang");
        subordinate2.setSalary(new BigDecimal("9500"));
        subordinate2.setCreatedTime(new Date());
        employee.getSubordinateList().add(subordinate2);

        XmlMapper mapper = new XmlMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String employeeXml = writer.writeValueAsString(employee);
        System.out.println("Java对象转成XML：");
        System.out.println(employeeXml);

        employee = mapper.readValue(employeeXml, Employee.class);
        System.out.println("XML还原成Java对象：");
        System.out.println(employee);
        System.out.println();

        employee = mapper.readValue(EMPLOYEE_XML, Employee.class);
        System.out.println("XML字符串转Java对象：");
        System.out.println(employee);
    }
}

@JsonInclude(NON_EMPTY) // 仅序列化值非空和非空字符串的字段
@JsonIgnoreProperties(ignoreUnknown = true) // 反序列化时忽略类中没有对应字段或Setter的XML元素
@JacksonXmlRootElement(localName = "Employee")
class Employee {
    @JacksonXmlText // 将属性直接作为未被标签包裹的普通文本表现
    @JacksonXmlProperty(localName = "XmlText")
    private String xmlText;

    @JacksonXmlCData // 将属性包裹在CDATA标签中
    @JacksonXmlProperty(localName = "XmlCData")
    private String xmlCData;
    /**
     * 工号
     */
    @JacksonXmlProperty(localName = "jobNum", isAttribute = true)
    private String jobNum;
    /**
     * 用户名
     */
    @JacksonXmlProperty(localName = "UserName")
    private String userName;
    /**
     * 姓名
     */
    @JacksonXmlProperty(localName = "FullName")
    private String fullName;
    /**
     * 性别
     */
    @JacksonXmlProperty(localName = "Gender")
    private String gender;
    /**
     * 年龄
     */
    @JacksonXmlProperty(localName = "Age")
    private Integer age;
    /**
     * 电话号码
     */
    @JacksonXmlProperty(localName = "Phone")
    private String phone;
    /**
     * 薪资
     */
    @JacksonXmlProperty(localName = "Salary")
    private BigDecimal salary;
    /**
     * 地址
     */
    @JacksonXmlProperty(localName = "Address")
    private String address;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JacksonXmlProperty(localName = "CreatedTime")
    private Date createdTime;
    /**
     * 下属员工
     */
    @JacksonXmlProperty(localName = "Subordinate")
    @JacksonXmlElementWrapper(localName = "SubordinateList")
    private List<Employee> subordinateList = new ArrayList<>();

    @JacksonXmlProperty(localName = "ValueIsEmpty") // 受@JsonInclude(NON_EMPTY)限制，此空值字段不会被序列化
    private String valueIsEmpty;

    @JsonIgnore // 表示此字段在序列化和反序列化的时候都将被忽略
    @JacksonXmlProperty(localName = "IgnoredClassField")
    private String ignoredClassField;

    public String getXmlText() {
        return xmlText;
    }

    public void setXmlText(String xmlText) {
        this.xmlText = xmlText == null ? null : xmlText.trim();
    }

    public String getXmlCData() {
        return xmlCData;
    }

    public void setXmlCData(String xmlCData) {
        this.xmlCData = xmlCData;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
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

    public List<Employee> getSubordinateList() {
        return subordinateList;
    }

    public void setSubordinateList(List<Employee> subordinateList) {
        this.subordinateList = subordinateList;
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

    @Override
    public String toString() {
        return "Employee{" + "xmlText='" + xmlText + '\'' + ", xmlCData='" + xmlCData + '\'' + ", jobNum='" + jobNum + '\'' + ", userName='" + userName + '\'' + ", fullName='" + fullName + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", phone='" + phone + '\'' + ", salary=" + salary + ", address='" + address + '\'' + ", createdTime=" + createdTime + ", subordinateList=" + subordinateList + ", valueIsEmpty='" + valueIsEmpty + '\'' + ", ignoredClassField='" + ignoredClassField + '\'' + '}';
    }
}