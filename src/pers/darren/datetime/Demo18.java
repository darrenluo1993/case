package pers.darren.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 字符串互转日期类型
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo18 {

	public static void main(final String[] args) {
		final LocalDateTime date = LocalDateTime.now();

		final DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		// 日期转字符串
		final String str = date.format(format1);

		System.out.println("日期转换为字符串:" + str);

		final DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		// 字符串转日期
		final LocalDate date2 = LocalDate.parse(str, format2);
		System.out.println("日期类型:" + date2);
	}
}