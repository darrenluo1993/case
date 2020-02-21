package pers.darren.datetime;

import java.time.Instant;

/**
 * 在Java 8中获取当前的时间戳
 *
 * <pre>
 * Instant类有一个静态工厂方法now()会返回当前的时间戳，如下所示：
 * 时间戳信息里同时包含了日期和时间，这和java.util.Date很像。实际上Instant类确实等同于
 * Java 8之前的Date类，你可以使用Date类和Instant类各自的转换方法互相转换，例如：Date.from(Instant)
 * 将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo16 {

	public static void main(final String[] args) {
		final Instant timestamp = Instant.now();
		System.out.println("What is value of this instant " + timestamp.toEpochMilli());
	}
}