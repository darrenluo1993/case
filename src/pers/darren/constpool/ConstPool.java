package pers.darren.constpool;

public class ConstPool {

	public Integer var = new Integer(4);

	public final String const1 = new String("1");

	public final String const2 = new String("2");

	public final String const3 = new String("3");

	public static void main(final String[] args) {
		final ConstPool pool1 = new ConstPool();
		System.out.println("--------------------------");
		final ConstPool pool2 = new ConstPool();

		System.out.println(pool1.var == pool2.var);
		System.out.println(pool1.const1 == pool2.const1);

		System.out.println("--------------------------");

		// 5种整形的包装类Byte,Short,Integer,Long,Character的对象
		// 在值小于127时可以使用常量池
		final Integer i1 = 127;
		final Integer i2 = 127;
		System.out.println(i1 == i2);// 输出true

		// 值大于127时，不会从常量池中取对象
		final Integer i3 = 128;
		final Integer i4 = 128;
		System.out.println(i3 == i4);// 输出false

		// Boolean类也实现了常量池技术
		final Boolean bool1 = true;
		final Boolean bool2 = true;
		System.out.println(bool1 == bool2);// 输出true

		// 浮点类型的包装类没有实现常量池技术
		final Double d1 = 1.0;
		final Double d2 = 1.0;
		System.out.println(d1 == d2);// 输出false
		final Float f1 = 1.0f;
		final Float f2 = 1.0f;
		System.out.println(f1 == f2);// 输出false

		System.out.println("--------------------------");

		final String hello = "Hello", lo = "lo";
		System.out.println(hello == "Hello");
		System.out.println(hello == "Hel" + "lo");
		System.out.println(hello == "Hel" + lo);
		System.out.println(hello == ("Hel" + lo).intern());
		System.out.println(hello == new String("Hello").intern());

		System.out.println("--------------------------");

		final String newStr1 = new String("Hello");
		final String newStr2 = new String("Hello");
		System.out.println(newStr1 == newStr2);
	}
}