package pers.darren.datetime;

import java.time.LocalDate;

/**
 * 如何在Java 8中检查闰年
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo14 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();
		if (today.isLeapYear()) {
			System.out.println("This year is Leap year");
		} else {
			System.out.println(today.getYear() + " is not a Leap year");
		}
	}
}