package pers.darren.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Date;

public class Serializable {

	public static void main(String[] args) throws Exception {
		// serializeString();
		serializeProduct();
		// deserializeProduct();
	}

	private static void serializeString() throws Exception {
		FileOutputStream fileOutStream = new FileOutputStream("/home/darren/Desktop/Temp Files/String.txt");
		ObjectOutputStream outStream = new ObjectOutputStream(fileOutStream);

		String string = "这是一个需要被序列化的字符串！";
		outStream.writeObject(string);
		outStream.flush();
		outStream.close();

		FileInputStream fileInStream = new FileInputStream("/home/darren/Desktop/Temp Files/String.txt");
		ObjectInputStream inStream = new ObjectInputStream(fileInStream);
		string = (String) inStream.readObject();
		inStream.close();
		System.out.println(string);
	}

	private static void serializeProduct() throws Exception {
		FileOutputStream fileOutStream = new FileOutputStream("/home/darren/Desktop/Temp Files/Product.txt");
		ObjectOutputStream outStream = new ObjectOutputStream(fileOutStream);

		Product product = new Product();
		product.setName("序列化与反序列化");
		product.setCategory("序列化");
		product.setSupplier("供应商");
		product.setQuantity(10000);
		product.setPrice(new BigDecimal(1000.5));
		product.setFeatured(true);
		product.setDescription("这是一个用于测试序列化与反序列化的产品！");
		product.setCreatedBy("Darren Luo");
		product.setCreatedTime(new Date());
		product.setModifiedBy("Darren Luo");
		product.setModifiedTime(new Date());
		System.out.println("Before>>>" + product);

		outStream.writeObject(product);
		outStream.flush();
		outStream.close();

		FileInputStream fileInStream = new FileInputStream("/home/darren/Desktop/Temp Files/Product.txt");
		ObjectInputStream inStream = new ObjectInputStream(fileInStream);
		product = (Product) inStream.readObject();
		inStream.close();
		System.out.println("After>>>" + product);
	}

	private static void deserializeProduct() throws Exception {
		FileInputStream fileInStream = new FileInputStream("/home/darren/Desktop/Temp Files/Product.txt");
		ObjectInputStream inStream = new ObjectInputStream(fileInStream);
		Product product = (Product) inStream.readObject();
		inStream.close();
		System.out.println("After>>>" + product);
	}
}