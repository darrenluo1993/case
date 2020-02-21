package pers.darren.datetime;

import java.time.Month;
import java.time.YearMonth;

/**
 * 如何表示信用卡到期这类固定日期，答案就在YearMonth
 *
 * <pre>
 * 与 MonthDay检查重复事件的例子相似，YearMonth是另一个组合类，用于表示信用卡到期
 * 日、FD到期日、期货期权到期日等。还可以用这个类得到 当月共有多少天，YearMonth实例
 * 的lengthOfMonth()方法可以返回当月的天数，在判断2月有28天还是29天时非常有用。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo13 {

	public static void main(final String[] args) {
		final YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		final YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
	}
}