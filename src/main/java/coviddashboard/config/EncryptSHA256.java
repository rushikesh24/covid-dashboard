package coviddashboard.config;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Java program to calculate SHA hash value  

public class EncryptSHA256 {
	public static String getSHA(String password) {
		MessageDigest md;
		String encryptPassword = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			encryptPassword = toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptPassword;
	}

	private static String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);

		StringBuilder hexString = new StringBuilder(number.toString(16));

		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}
}