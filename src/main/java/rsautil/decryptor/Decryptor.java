package rsautil.decryptor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class Decryptor {
	
	private PrivateKey privateKey;
	
	public Decryptor(String privateKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		File privateKeyFile = new File(privateKeyPath);
		byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		privateKey = keyFactory.generatePrivate(privateKeySpec);
	}
	
	public String decrypt(String string) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		byte[] encryptedMessageBytes = Base64.getDecoder().decode(string);
		
		Cipher decryptCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1",
                MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
		
		decryptCipher.getAlgorithm();
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParameterSpec);
		
		byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
		return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
	}

}
