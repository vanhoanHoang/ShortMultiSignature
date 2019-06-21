package oodrive.com.phd.MultiSignature.Engines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import oodrive.com.phd.MultiSignature.Parameters.MSPrivateKey;
import oodrive.com.phd.MultiSignature.Parameters.MSSignature;
import oodrive.com.phd.MultiSignature.Parameters.SystemParameters;

public class SigningEngine {

	public SystemParameters systemParams;

	public SigningEngine(SystemParameters systemParams) {
		this.systemParams = systemParams;
	}

	public MSSignature sign(String data, MSPrivateKey msPrivateKey) throws NoSuchAlgorithmException {

		Pairing pairing = systemParams.getPairing();

		Element privateKey = msPrivateKey.getPrivateKey().duplicate();
		Element convertedData = pairing.getG1().newRandomElement();

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashedData = md.digest(data.getBytes());

		convertedData.setFromHash(hashedData, 0, hashedData.length);
		convertedData.powZn(privateKey);
		return new MSSignature(convertedData);
	}

	public MSSignature aggregate(ArrayList<MSSignature> signatureList) {
		Pairing pairing = systemParams.getPairing();
		Element aggregatedAlpha = pairing.getG1().newRandomElement();

		for (int i = 0; i < signatureList.size(); i++) {
			if (i == 0) {
				aggregatedAlpha = signatureList.get(i).getAlpha().duplicate();
			}
			aggregatedAlpha.mul(signatureList.get(i).getAlpha());
		}

		return new MSSignature(aggregatedAlpha);
	}

}
