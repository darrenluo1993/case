package pers.darren.datetime;

import java.time.LocalDate;

/**
 * Java 8中获取年、月、日信息
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:48:26 PM
 */
public class Demo2 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();
		final int year = today.getYear();
		final int month = today.getMonthValue();
		final int day = today.getDayOfMonth();

		System.out.println("year:" + year);
		System.out.println("month:" + month);
		System.out.println("day:" + day);
	}
}