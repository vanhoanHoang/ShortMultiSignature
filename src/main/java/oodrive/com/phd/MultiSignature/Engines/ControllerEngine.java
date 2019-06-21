package oodrive.com.phd.MultiSignature.Engines;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import oodrive.com.phd.MultiSignature.Entity.MSUser;
import oodrive.com.phd.MultiSignature.Parameters.MSPublicKey;
import oodrive.com.phd.MultiSignature.Parameters.MSSignature;
import oodrive.com.phd.MultiSignature.Parameters.SystemParameters;

public class ControllerEngine {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SystemParameters systemParams = new SystemParameters();
		SigningEngine signingEngine = new SigningEngine(systemParams);
		VerifyingEngine verifyingEngine = new VerifyingEngine(systemParams);
		
		ArrayList<MSPublicKey> publicKeyList = new ArrayList<MSPublicKey>();
		ArrayList<String> dataToSign = new ArrayList<String>();
		
		MSUser userA = new MSUser(systemParams); 
		userA.generateKeyPair();
		
		String data = new String("Data to sign");
		
		MSSignature msSignature = signingEngine.sign(data, userA.getMSSKey());
		
		publicKeyList.add(userA.getMSPKey());
		dataToSign.add(data);
		boolean validity = verifyingEngine.verify(msSignature, publicKeyList, dataToSign);
		
		System.out.println("Validity of the signature:"+validity);
		
	}
}
