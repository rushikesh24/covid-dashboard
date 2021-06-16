package coviddashboard.config;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class TripleDES {

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;

	// password = rushikesh@24
	public TripleDES() throws Exception {
		this.myEncryptionKey = "ThisIsSpartaThisIsSparta";
		this.myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		this.arrayBytes = this.myEncryptionKey.getBytes(UNICODE_FORMAT);
		this.ks = new DESedeKeySpec(this.arrayBytes);
		this.skf = SecretKeyFactory.getInstance(this.myEncryptionScheme);
		this.cipher = Cipher.getInstance(this.myEncryptionScheme);
		this.key = this.skf.generateSecret(this.ks);
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = this.cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = this.cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
}