package pers.darren.datetime;

import java.time.LocalDate;

/**
 * Java 8中获取今天的日期
 *
 * <pre>
 * Java 8 中的 LocalDate 用于表示当天日期，
 * 和java.util.Date不同，它只有日期，不包含时间，当你仅需要表示日期时就用这个类。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:47:28 PM
 */
public class Demo1 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();
		System.out.println("今天的日期:" + today);
	}
}