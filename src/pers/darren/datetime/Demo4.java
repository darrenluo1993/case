package pers.darren.datetime;

import java.time.LocalDate;

/**
 * Java 8中判断两个日期是否相等
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:45:22 PM
 */
public class Demo4 {

	public static void main(final String[] args) {
		final LocalDate date1 = LocalDate.now();

		final LocalDate date2 = LocalDate.of(2018, 2, 5);

		if (date1.equals(date2)) {
			System.out.println("时间相等");
		} else {
			System.out.println("时间不等");
		}
	}
}