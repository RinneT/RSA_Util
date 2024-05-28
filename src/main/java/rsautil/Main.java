package rsautil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rsautil.decryptor.Decryptor;
import rsautil.keygen.KeyGenerator;
import rsautil.model.Participant;

public class Main {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeySpecException,
			InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if (args != null && args.length == 1) {
			if ("genkey".contentEquals(args[0])) {
				KeyGenerator.generateKeys();
			}
		} else if (args != null && args.length == 3) {
			if ("decrypt".contentEquals(args[0])) {
				String privateKeyPath = args[1];
				String participantsFilePath = args[2];

				ObjectMapper objectMapper = new ObjectMapper();

				File file = new File(participantsFilePath);

				List<Participant> participants = objectMapper.readValue(file, new TypeReference<List<Participant>>() {
				});

				Decryptor decr = new Decryptor(privateKeyPath);
				for (Participant participant : participants) {
					decryptParticipant(participant, decr);
				}

				objectMapper.writeValue(new FileOutputStream(participantsFilePath.replace(".json", ".decrypted.json")),
						participants);
			}
		}

	}

	private static void decryptParticipant(Participant participant, Decryptor decr) {
		String lastName = participant.getLastName();
		String phone = participant.getMobilePhone();
		String eMail = participant.geteMail();
		
		try {
			String decrypted = decr.decrypt(lastName);
			participant.setLastName(decrypted);
		} catch (Exception e) {
			System.out.println("Warning: Could not decrypt last name: " + lastName);
			printStacktrace(e);
		}

		try {
			String decrypted = decr.decrypt(phone);
			participant.setMobilePhone(decrypted);
		} catch (Exception e) {
			System.out.println("Warning: Could not decrypt mobile phone " + phone);
			printStacktrace(e);
		}

		try {
			String decrypted = decr.decrypt(eMail);
			participant.seteMail(decrypted);
		} catch (Exception e) {
			System.out.println("Warning: Could not decrypt eMail: " + eMail);
			printStacktrace(e);
		}

	}
	
	private static void printStacktrace(Exception e) {
		System.out.println(e.getMessage());
		for (StackTraceElement element : e.getStackTrace()) {
			System.out.println(element.toString());
		}
	}

}
