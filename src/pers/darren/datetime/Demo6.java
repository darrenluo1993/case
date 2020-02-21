package pers.darren.datetime;

import java.time.LocalTime;

/**
 * Java 8中获取当前时间
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:10 PM
 */
public class Demo6 {

	public static void main(final String[] args) {
		final LocalTime time = LocalTime.now();
		System.out.println("获取当前的时间,不含有日期:" + time);
	}
}