package pers.darren.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;
import static pers.darren.jackson.JacksonCase.*;


@TestMethodOrder(OrderAnnotation.class)
@DisabledOnOs(MAC)
@EnabledOnOs(value = LINUX, architectures = "amd64")
@DisabledOnJre(JAVA_8)
@EnabledOnJre(JAVA_18)
@EnabledForJreRange(min = JAVA_14, max = JAVA_18)
@DisabledForJreRange(min = JAVA_8, max = JAVA_13)
@DisplayName("FasterXML Jackson test case")
public class JacksonCaseTests {
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @Order(10)
    @TestOnAmd64Linux
    // @TestOnAmd64Linux equal to @Test and @EnabledOnOs(value = LINUX, architectures = "amd64")
    public void string2ObjectAndListTest() throws JsonProcessingException {
        string2ObjectAndList();
    }

    @Test
    @Order(20)
    public void inputStream2ObjectAndListTest() throws IOException {
        inputStream2ObjectAndList();
    }

    @Test
    @Order(30)
    public void file2ObjectAndListTest() throws IOException {
        file2ObjectAndList();
    }

    @Test
    @Order(40)
    public void byteArray2ObjectAndListTest() throws Exception {
        byteArray2ObjectAndList();
    }

    @Test
    @Order(50)
    public void jsonArray2ObjectArrayTest() throws Exception {
        jsonArray2ObjectArray();
    }

    @Test
    @Order(60)
    public void string2MapAndListTest() throws JsonProcessingException {
        string2MapAndList();
    }

    @Test
    @Order(70)
    public void string2JsonNodeTest() throws JsonProcessingException {
        string2JsonNode();
    }

    @Test
    @Order(80)
    public void objectAndList2JsonTest() throws IOException {
        objectAndList2Json();
    }

    @Test
    @Order(90)
    public void convertValueTest() throws JsonProcessingException {
        convertValue();
    }

    @Test
    @Order(100)
    public void jsonGeneratorParserTest() throws IOException {
        jsonGeneratorParser();
    }

    @Test
    @Order(105)
    public void valueToTreeAndTreeToValueTest() throws Exception {
        valueToTreeAndTreeToValue();
    }

    @Test
    @Order(110)
    // @EnabledIf("customCondition")
    @EnabledIf("pers.darren.jackson.ExternalCondition#customCondition")
    public void enabledIfTest() {
        System.out.println("Come in enabledIf");
    }

    @Test
    @Order(120)
    // @DisabledIf("customCondition")
    @DisabledIf("pers.darren.jackson.ExternalCondition#customCondition")
    public void disabledIfTest() {
        System.out.println("Come in disabledIf");
    }

    private boolean customCondition() {
        return true;
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@EnabledOnOs(value = LINUX, architectures = "amd64")
@interface TestOnAmd64Linux {
}

class ExternalCondition {
    static boolean customCondition() {
        return true;
    }
}

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class OrderedNestedTestClassesDemo {

    @Nested
    @Order(1)
    class PrimaryTests {
        @Test
        void test1() {
            System.out.println("test1");
        }
    }

    @Nested
    @Order(2)
    class SecondaryTests {
        @Test
        void test2() {
            System.out.println("test2");
        }
    }
}