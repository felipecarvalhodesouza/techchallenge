package br.com.postech.techchallenge.infraestrutura.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class EncodingUtils {
	
	private EncodingUtils() {}

	public static String hashData(String data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
		return DatatypeConverter.printHexBinary(hashBytes);
	}
}
