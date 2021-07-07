package pers.darren.test;

import java.math.BigDecimal;

public class Test {
    public static void main(final String[] args) {
        // new BigDecimal(10).add(null);

        System.out.println(new BigDecimal(20.00).toPlainString());
        System.out.println(new BigDecimal("20.00").stripTrailingZeros().toPlainString());

        System.out.println(4 % 3); // 1
        System.out.println(4 / 3); // 1
    }
}