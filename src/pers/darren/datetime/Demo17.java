package pers.darren.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Java 8中如何使用预定义的格式化工具去解析或格式化日期
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo17 {

	public static void main(final String[] args) {
		final String dayAfterTommorrow = "20180205";
		final LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);
	}
}