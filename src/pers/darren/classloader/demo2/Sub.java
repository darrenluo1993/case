package pers.darren.classloader.demo2;

public class Sub extends Parent {

	{
		System.out.println("D - This is Sub's non-static block!");
	}

	static {
		System.out.println("E - This is Sub's static block!");
	}

	public Sub() {
		System.out.println("F - This is Sub's contructor!");
	}
}