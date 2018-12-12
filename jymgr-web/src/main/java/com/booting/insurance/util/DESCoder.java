package com.booting.insurance.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCoder extends Coder {
	/**
	 * ALGORITHM 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * <pre>
	 * DES                  key size must be equal to 56 
	 * DESede(TripleDES)    key size must be equal to 112 or 168 
	 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
	 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
	 * RC2                  key size must be between 40 and 1024 bits 
	 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
	 * </pre>
	 * 
	 * 在Key toKey(byte[] key)方法中使用下述代码
	 * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
	 * <code> 
	 * DESKeySpec dks = new DESKeySpec(key); 
	 * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
	 * SecretKey secretKey = keyFactory.generateSecret(dks); 
	 * </code>
	 */
	public static final String ALGORITHM = "DES";

	public static final String key = "szyt@2015";

	/**
	 * 转换密钥<br>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);

		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);

		return cipher.doFinal(data);
	}

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initKey() throws Exception {
		return initKey(null);
	}

	/**
	 * 生成密钥
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String initKey(String seed) throws Exception {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(decryptBASE64(seed));
		} else {
			secureRandom = new SecureRandom();
		}

		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		kg.init(secureRandom);

		SecretKey secretKey = kg.generateKey();

		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * 获取加密后字符串
	 * 
	 * @param userbm
	 * @return
	 * @throws Exception
	 */
	public static String getEncryptToken(String inputData) throws Exception {
         byte[] in = inputData.getBytes();  
         String token_new = Coder.encryptBASE64(in); 
		if(token_new.contains("\r\n")){
			token_new=token_new.replace("\r\n", "");
		}else if(token_new.contains("\n")){
			token_new=token_new.replace("\n", "");
		}else if(token_new.contains("\r")){
			token_new=token_new.replace("\r", "");
		}
		return token_new;
	}
	
	/**
	 * 获取加密后字节
	 * 
	 * @param userbm
	 * @return
	 * @throws Exception
	 */
	public static String getEncryptToken(byte[] data) throws Exception {
        String token_new = Coder.encryptBASE64(data); 
		if(token_new.contains("\r\n")){
			token_new=token_new.replace("\r\n", "");
		}else if(token_new.contains("\n")){
			token_new=token_new.replace("\n", "");
		}else if(token_new.contains("\r")){
			token_new=token_new.replace("\r", "");
		}
		return token_new;
	}
	
	/**
	 * 解密token
	 * 
	 * @param userbm
	 * @return
	 * @throws Exception
	 */
	public static String getDecryptToken(String inputData) throws Exception {
         
		byte[] output = Coder.decryptBASE64(inputData); 
		String outputStr = new String(output); 
		return outputStr;
	}
    /**
     * 校验用户userbm是否存在
     * @param token
     * @param userbm
     * @return
     * @throws Exception
     */
	public static boolean checkToken(String token, String userbm)
			throws Exception {
		byte[] inputData = userbm.getBytes();  
        String token_new = Coder.encryptBASE64(inputData); 
		if(token_new.contains("\r\n")){
			token_new=token_new.replace("\r\n", "");
		}else if(token_new.contains("\n")){
			token_new=token_new.replace("\n", "");
		}else if(token_new.contains("\r")){
			token_new=token_new.replace("\r", "");
		}
		if(token.equals(token_new)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		String password = "2aa42529";
		try {
			String token= DESCoder.getEncryptToken(password);
			System.out.println(token);
			String output = DESCoder.getDecryptToken(token);
			System.out.println(output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
