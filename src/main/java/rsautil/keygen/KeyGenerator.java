package rsautil.keygen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyGenerator {
	
	public static void generateKeys() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		try (FileOutputStream fos = new FileOutputStream("public.key")) {
		    fos.write(publicKey.getEncoded());
		}
		
		try (FileOutputStream fos = new FileOutputStream("private.key")) {
		    fos.write(privateKey.getEncoded());
		}
	}
}
