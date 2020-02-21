package pers.darren.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Java 8计算一年前或一年后的日期
 *
 * <pre>
 * 利用minus()方法计算一年前的日期
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo9 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();

		final LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
		System.out.println("一年前的日期 : " + previousYear);

		final LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
		System.out.println("一年后的日期:" + nextYear);
	}
}