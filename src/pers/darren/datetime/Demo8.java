package pers.darren.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Java 8如何计算一周后的日期
 *
 * <pre>
 * 和上个例子计算3小时以后的时间类似，这个例子会计算一周后的日期。LocalDate日期不包含
 * 时间信息，它的plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。由于
 * LocalDate也是不变类型，返回后一定要用变量赋值。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo8 {

	public static void main(final String[] args) {
		final LocalDate today = LocalDate.now();
		System.out.println("今天的日期为:" + today);
		final LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("一周后的日期为:" + nextWeek);
		final LocalDate againNextWeek = nextWeek.plusWeeks(1);
		System.out.println("再一周后的日期为:" + againNextWeek);
	}
}