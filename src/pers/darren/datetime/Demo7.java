package pers.darren.datetime;

import java.time.LocalTime;

/**
 * Java 8中获取当前时间
 *
 * <pre>
 * 通过增加小时、分、秒来计算将来的时间很常见。Java 8除了不变类型和线程安全的好处之
 * 外，还提供了更好的plusHours()方法替换add()，并且是兼容的。注意，这些方法返回
 * 一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 21, 2020 12:51:33 PM
 */
public class Demo7 {

	public static void main(final String[] args) {
		final LocalTime time = LocalTime.now();
		final LocalTime newTime = time.plusHours(3);
		System.out.println("三个小时后的时间为:" + newTime);
	}
}