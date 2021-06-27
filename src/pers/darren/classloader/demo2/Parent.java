package pers.darren.classloader.demo2;

public class Parent {

	{
		System.out.println("A - This is Parent's non-static block!");
	}

	static {
		System.out.println("B - This is Parent's static block!");
	}

	public Parent() {
		System.out.println("C - This is Parent's contructor!");
	}
}