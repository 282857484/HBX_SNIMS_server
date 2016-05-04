package tool;
import handlemethod.handle;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncrypRSA {

	/**
	 * RSA
	 * 加密
	 * 
	 * @param publicKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	protected byte[] encrypt(PublicKey publicKey, byte[] srcBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (publicKey != null) {
			// Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// 根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	/**
	 * RSA
	 * 解密
	 * 
	 * @param privateKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	protected byte[] decrypt(PrivateKey privateKey, byte[] srcBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (privateKey != null) {
			// Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// 根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
	InvalidKeyException, NoSuchPaddingException,
	IllegalBlockSizeException, BadPaddingException {
		EncrypRSA rsa = new EncrypRSA();
		String msg = "侯斌b46hergwhw4ywWrg";
		KeyPair keyPair = getKeyPair();
		PrivateKey  privateKey = (PrivateKey) keyPair.getPrivate();
//		System.out.println("privateKey: \n" + privateKey);
		// 得到公钥
		PublicKey  publicKey = (PublicKey) keyPair.getPublic();
//		RSAPublicKey ss = new  RSAPublicKey();
		System.out.println("publicKey: \n" + publicKey);
		String pukey = handle.g.toJson(publicKey);
		publicKey = handle.g.fromJson(pukey, PublicKey.class);
		
		String enmsg = enCodeWhatTheFuckMaker(msg);
		// 用公钥加密
		byte[] srcBytes = enmsg.getBytes();
		System.out.println("msg-byte : " + srcBytes.length);
		System.out.println("明文是:" + msg);
		byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);
		System.out.println("加密后是:" + new String(resultBytes));
		
		// 用私钥解密
		byte[] decBytes = rsa.decrypt(privateKey, resultBytes);
		System.out.println("解密后是:" + deCodeCodeWhatTheFuckMaker(new String(decBytes)));
	}
//	/**
//	 * @param args
//	 * @throws NoSuchAlgorithmException
//	 * @throws BadPaddingException
//	 * @throws IllegalBlockSizeException
//	 * @throws NoSuchPaddingException
//	 * @throws InvalidKeyException
//	 */
//	public static void main(String[] args) throws NoSuchAlgorithmException,
//			InvalidKeyException, NoSuchPaddingException,
//			IllegalBlockSizeException, BadPaddingException {
//		EncrypRSA rsa = new EncrypRSA();
//		String msg = "侯斌b46hergwhw4ywWrg";
//		
//////		String msg1 = "WTF"+msg;
////		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
////		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
////		// 初始化密钥对生成器，密钥大小为1024位
////		keyPairGen.initialize(1022);
////		// 生成一个密钥对，保存在keyPair中
//		KeyPair keyPair = getKeyPair();
//		// 得到私钥
//		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//		System.out.println("privateKey:" + privateKey);
//		// 得到公钥
//		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		System.out.println("publicKey:" + publicKey);
//
//		String enmsg = enCodeWhatTheFuckMaker(msg);
//		// 用公钥加密
//		byte[] srcBytes = enmsg.getBytes();
//		System.out.println("msg-byte : " + srcBytes.length);
//		System.out.println("明文是:" + msg);
//		byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);
////		byte[] b = resultBytes;
////		String sss = new String(resultBytes);
////		System.out.println("sss是:" + sss);
////		String myCode = sss.toString().subSequence(0, sss.length()/3).toString() 
////				+ sss.toString().subSequence((sss.length()/3 + 1),(2*sss.length())/3 + 1).toString()
////				+ sss.toString().subSequence(((2*sss.length())/3 + 1), sss.length() +1).toString();
//		System.out.println("加密后是:" + new String(resultBytes));
////		byte[] resultBytes2 = rsa.encrypt(publicKey, resultBytes);
////		System.out.println("加密后是2:" + new String(resultBytes2));
//		
//		// 用私钥解密
//		byte[] decBytes = rsa.decrypt(privateKey, resultBytes);
//		System.out.println("解密后是:" + deCodeCodeWhatTheFuckMaker(new String(decBytes)));
////		byte[] decBytes2 = rsa.decrypt(privateKey, decBytes);
////		System.out.println("解密后是2:" + new String(decBytes2));
//	}
	
	public static KeyPair keyPair ;
	public static KeyPair getKeyPair() {
		KeyPairGenerator keyPairGen;
		if(keyPair == null)
			try {
				// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
				keyPairGen = KeyPairGenerator.getInstance("RSA");
				// 初始化密钥对生成器，密钥大小为1024位
				keyPairGen.initialize(1022);
				// 生成一个密钥对，保存在keyPair中
				keyPair = keyPairGen.generateKeyPair();
				return keyPair;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				logger.error
			} 
		else 
			return keyPair;

		return null;
	}

	public static String enCodeWhatTheFuckMaker(String generalString){
		return "WTF" + generalString;
	}
	public static String deCodeCodeWhatTheFuckMaker(String decString) {
		return decString.substring(3);
	}
	
}