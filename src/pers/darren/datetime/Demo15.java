package pers.darren.datetime;

import java.time.LocalDate;
import java.time.Period;

/**
 * 计算两个日期之间的天数和月数
 *
 * <pre>
 * 有一个常见日期操作是计算两个日期之间的天数、周数或月数。在Java 8中可以用
 * java.time.Period类来做计算。下面这个例子中，我们计算了当天和将来某一天之间的月数。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo15 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();

		final LocalDate java8Release = LocalDate.of(2018, 12, 14);

		final Period periodToNextJavaRelease = Period.between(today, java8Release);
		System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());
	}
}