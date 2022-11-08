package pers.darren.bytecode.asm.simple;

import com.fasterxml.jackson.annotation.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
class Base {

    @JsonProperty("NAME")
    private String name;
    @JsonProperty("GENDER")
    private String gender;
    @JsonProperty("AGE")
    private Integer age;

    public Base() {
    }

    @JsonCreator
    public Base(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    @JsonValue(false)
    public String toString() {
        return "Base{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + ", age=" + age + '}';
    }
}
