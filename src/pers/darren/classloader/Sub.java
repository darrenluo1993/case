package pers.darren.classloader;

public class Sub extends Parent {
	static {
		System.out.println("This is sub!");
	}
}