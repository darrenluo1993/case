package pers.darren.datetime;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * Java 8中检查像生日这种周期性事件
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:50:07 PM
 */
public class Demo5 {

	public static void main(final String[] args) {
		final LocalDate date1 = LocalDate.now();

		final LocalDate date2 = LocalDate.of(2018, 2, 6);
		final MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
		final MonthDay currentMonthDay = MonthDay.from(date1);

		if (currentMonthDay.equals(birthday)) {
			System.out.println("是你的生日");
		} else {
			System.out.println("你的生日还没有到");
		}
	}
}