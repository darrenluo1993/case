package pers.darren.classloader.demo1;

public class Loader {

	public static void main(String[] args) {
		System.out.println("------------------------------------------");
		System.out.println(Parent.NAME);
		try {
			// Class.forName("pers.darren.classloader.demo1.Parent");
			// Loader.class.getClassLoader().loadClass("pers.darren.classloader.demo1.Sub");
			// ClassLoader.getSystemClassLoader().loadClass("pers.darren.classloader.demo1.Sub");
			System.out.println("------------------------------------------");
			Class.forName("pers.darren.classloader.demo1.Sub");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------------");
	}
}