package oodrive.com.phd.MultiSignature.Engines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import oodrive.com.phd.MultiSignature.Parameters.MSPublicKey;
import oodrive.com.phd.MultiSignature.Parameters.MSSignature;
import oodrive.com.phd.MultiSignature.Parameters.SystemParameters;

public class VerifyingEngine {

	public SystemParameters systemParams;

	public VerifyingEngine(SystemParameters systemParams) {
		this.systemParams = systemParams;
	}

	public boolean verify(MSSignature signature, ArrayList<MSPublicKey> pKey, ArrayList<String> data) throws NoSuchAlgorithmException {
		Element g = systemParams.getGenerator().duplicate();
		Pairing pairing = systemParams.getPairing();

		Element p1 = pairing.pairing(g, signature.getAlpha());
		Element p2 = pairing.getGT().newOneElement();

		for (int i = 0; i < pKey.size(); i++) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] hashedData = md.digest(data.get(i).getBytes()); 

			Element hashedElement = pairing.getG1().newRandomElement();
			hashedElement.setFromHash(hashedData, 0, hashedData.length);
			
			p2.mul(pairing.pairing(pKey.get(i).getPublicKey(), hashedElement));
		}
		
		return p1.isEqual(p2);

	}

}
