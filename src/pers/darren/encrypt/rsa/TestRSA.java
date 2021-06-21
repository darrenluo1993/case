package pers.darren.encrypt.rsa;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class TestRSA {

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 获取密钥对
	 *
	 * @return 密钥对
	 */
	public static KeyPair getKeyPair() throws Exception {
		final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		return generator.generateKeyPair();
	}

	/**
	 * 获取私钥
	 *
	 * @param privateKey 私钥字符串
	 * @return
	 */
	public static PrivateKey getPrivateKey(final String privateKey) throws Exception {
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 获取公钥
	 *
	 * @param publicKey 公钥字符串
	 * @return
	 */
	public static PublicKey getPublicKey(final String publicKey) throws Exception {
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * RSA加密
	 *
	 * @param data      待加密数据
	 * @param publicKey 公钥
	 * @return
	 */
	public static String encrypt(final String data, final PublicKey publicKey) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		final int inputLen = data.getBytes().length;
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_ENCRYPT_BLOCK;
		}
		final byte[] encryptedData = out.toByteArray();
		out.close();
		// 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
		// 加密后的字符串
		return new String(Base64.encodeBase64String(encryptedData));
	}

	/**
	 * RSA解密
	 *
	 * @param data       待解密数据
	 * @param privateKey 私钥
	 * @return
	 */
	public static String decrypt(final String data, final PrivateKey privateKey) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		final byte[] dataBytes = Base64.decodeBase64(data);
		final int inputLen = dataBytes.length;
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_DECRYPT_BLOCK;
		}
		final byte[] decryptedData = out.toByteArray();
		out.close();
		// 解密后的内容
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * 签名
	 *
	 * @param data       待签名数据
	 * @param privateKey 私钥
	 * @return 签名
	 */
	public static String sign(final String data, final PrivateKey privateKey) throws Exception {
		final byte[] keyBytes = privateKey.getEncoded();
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final PrivateKey key = keyFactory.generatePrivate(keySpec);
		final Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(key);
		signature.update(data.getBytes());
		return new String(Base64.encodeBase64(signature.sign()));
	}

	/**
	 * 验签
	 *
	 * @param srcData   原始字符串
	 * @param publicKey 公钥
	 * @param sign      签名
	 * @return 是否验签通过
	 */
	public static boolean verify(final String srcData, final PublicKey publicKey, final String sign) throws Exception {
		final byte[] keyBytes = publicKey.getEncoded();
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final PublicKey key = keyFactory.generatePublic(keySpec);
		final Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(key);
		signature.update(srcData.getBytes());
		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}

	public static void main(final String[] args) {
		try {
			// 生成密钥对
			final KeyPair keyPair = getKeyPair();
			final String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
			final String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
			System.out.println("私钥:" + privateKey);
			System.out.println("公钥:" + publicKey);
			// RSA加密
			final String data = "my password";
			final String encryptData = encrypt(data, getPublicKey(publicKey));
			System.out.println("加密后内容:" + encryptData);
			// RSA解密
			final RSAPrivateKey privateKeyReal = (RSAPrivateKey) getPrivateKey(privateKey);
			System.out.println("私钥N值：" + privateKeyReal.getModulus().toString());
			System.out.println("私钥E值：" + privateKeyReal.getPrivateExponent().toString());
			final String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
			System.out.println("解密后内容:" + decryptData);

			// RSA签名
			final String sign = sign(data, getPrivateKey(privateKey));
			// RSA验签
			final boolean result = verify(data, getPublicKey(publicKey), sign);
			System.out.print("验签结果:" + result);
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}
}