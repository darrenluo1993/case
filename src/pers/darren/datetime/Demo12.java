package pers.darren.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Java 8中处理时区
 *
 * <pre>
 * Java 8不仅分离了日期和时间，也把时区分离出来了。现在有一系列单独的类如ZoneId来处理
 * 特定时区，ZoneDateTime类来表示某时区下的时间。这在Java 8以前都是GregorianCalendar
 * 类来做的。下面这个例子展示了如何把本时区的时间转换成另一个时区的时间。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo12 {

	public static void main(final String[] args) {
		// Date and time with timezone in Java 8
		final ZoneId america = ZoneId.of("America/New_York");
		final LocalDateTime localtDateAndTime = LocalDateTime.now();
		final ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
	}
}