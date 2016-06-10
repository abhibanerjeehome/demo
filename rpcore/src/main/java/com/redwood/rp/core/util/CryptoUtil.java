package com.redwood.rp.core.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Named;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class CryptoUtil {
	
	private static final String FILENAME = CryptoUtil.class.getName();

	private static Logger logger = LoggerFactory.getLogger(FILENAME);	
	
	private static final String TRANSFORMATION = "Blowfish/ECB/PKCS5Padding";
	
	/**
	 * Method to encrypt the passed plain text
	 * @param iPlainText the string to be encrypted
	 * @param iKeyString the pass phrase used for encryption
	 * @return String : the encrypted string
	 */
	public String encryptString(final String iPlainText, final String iKeyString) {
		String theCipherText = null;
		try {
			if (StringUtils.isBlank(iPlainText) || StringUtils.isBlank(iKeyString)) {
				throw new IllegalArgumentException("The mandatory parameters are missing for encryption");
			}	
			final Cipher aCipher = getCipher(iKeyString, Cipher.ENCRYPT_MODE);
			final ByteArrayOutputStream anOutputStream = new ByteArrayOutputStream();
			final CipherOutputStream aCipherOutputStream = new CipherOutputStream(anOutputStream, aCipher);
			aCipherOutputStream.write(iPlainText.getBytes());
			aCipherOutputStream.close();
			anOutputStream.close();
			byte[] theCipherData = anOutputStream.toByteArray();
			theCipherText = new String(Hex.encodeHex(theCipherData));
		} catch (final Exception anException) {
			logger.error("ENCRYPTION : An error occurred when encrypting the plain text " + iPlainText + " with key " + iKeyString + ". The cause is : ", anException);
		}
		return theCipherText;
	}
	
	/**
	 * Method to fetch the cipher for encryption / decryption
	 * @param iKeyString
	 * @param iMode
	 * @return Cipher
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	private Cipher getCipher(final String iKeyString, final int iMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
		final byte[] theKeyStringBytes = iKeyString.getBytes();
		final SecretKeySpec aSecretKeySpec = new SecretKeySpec(theKeyStringBytes, "Blowfish");

		try {
			final Cipher aCipher = Cipher.getInstance(TRANSFORMATION);
			aCipher.init(iMode, aSecretKeySpec);
			return aCipher;
		} catch (final NoSuchPaddingException aNoSuchPaddingException) {
			logger.error("Padding for transformation '" + TRANSFORMATION + "' is not available", aNoSuchPaddingException);
			throw aNoSuchPaddingException;
		} catch (final NoSuchAlgorithmException aNoSuchAlgorithmException) {
			logger.error("Algorithm for transformation '" + TRANSFORMATION + "' is not available", aNoSuchAlgorithmException);
			throw aNoSuchAlgorithmException;
		} catch (final InvalidKeyException aInvalidKeyException) {
			logger.error("Provided key is invalid for transformation '" + TRANSFORMATION + "", aInvalidKeyException);
			throw aInvalidKeyException;
		}
	}
	
	/**
	 * Takes a String that is the hex-encoded version of the binary encrypted data and
	 * un-hex-encodes it to a byte stream and decrypts that byte stream.
	 * 
	 * @param iCiphertextHex the encrypted text to be decrypted
	 * @param iKeyString the pass phrase used for decryption
	 * @return String : the decrypted (clear text) string 
	 */
	public String decryptString(final String iCiphertextHex, final String iKeyString) {
		String aClearText = null;
		try {
			if (StringUtils.isBlank(iCiphertextHex) || StringUtils.isBlank(iKeyString)) {
				throw new IllegalArgumentException("The mandatory parameters are missing for decryption");
			}
			final byte[] theCipherText = Hex.decodeHex(iCiphertextHex.toCharArray());
			final Cipher aCipher = getCipher(iKeyString, Cipher.DECRYPT_MODE);
			final ByteArrayOutputStream anOutputStream = new ByteArrayOutputStream();
			final CipherOutputStream aCipherOutputStream = new CipherOutputStream(anOutputStream, aCipher);
			aCipherOutputStream.write(theCipherText);
			aCipherOutputStream.close();
			anOutputStream.close();
			byte[] theClearText = anOutputStream.toByteArray();
			aClearText = new String(theClearText);
		} catch (final Exception anException) {
			logger.error("DECRYPTION : An error occurred when decrypting the cipher text " + iCiphertextHex + " with key " + iKeyString + ". The cause is : ", anException);
		}
		return aClearText;
	}	
	
	

}
