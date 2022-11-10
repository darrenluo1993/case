package pers.darren.conversion;

import java.util.Arrays;

import static java.lang.Integer.toHexString;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ToHexadecimal {

    public static void main(String[] args) throws Exception {
        string2Hex();
    }

    private static void string2Hex() {
        byte[] bytes = "系统名称".getBytes(UTF_8);
        System.out.println(Arrays.toString(bytes));
        for (byte aByte : bytes) {
            System.out.print(toHexString(aByte).substring(6).toUpperCase() + " ");
        }
    }
}
