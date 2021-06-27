package pers.darren.classloader.demo1;

public class Parent {

	public static final String NAME = "PARENT";

	static {
		System.out.println("This is parent!");
	}
}