package oodrive.com.phd.MultiSignature.Entity;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import oodrive.com.phd.MultiSignature.Parameters.MSPrivateKey;
import oodrive.com.phd.MultiSignature.Parameters.MSPublicKey;
import oodrive.com.phd.MultiSignature.Parameters.SystemParameters;

public class MSUser {
	private MSPrivateKey msSKey;
	public MSPublicKey msPKey;
	public boolean generated; // This is to verify whether user has generated its keypair
	public SystemParameters systemParams;

	public MSUser(SystemParameters systemParams) {
		this.systemParams = systemParams;
	}

	public MSPrivateKey getMSSKey() {
		return msSKey;
	}

	public MSPublicKey getMSPKey() {
		return msPKey;
	}

	public void setMSSKey(MSPrivateKey msSKey) {
		this.msSKey = msSKey;
	}

	public void setMSPKey(MSPublicKey msPKey) {
		this.msPKey = msPKey;
	}

	public void generateKeyPair() {
		if (generated)
			return;
		Pairing pairing = systemParams.getPairing();
		Element g = systemParams.getGenerator().duplicate();

		Element secret = pairing.getZr().newRandomElement();
		Element publicKey = g.powZn(secret).duplicate();

		msSKey = new MSPrivateKey(secret);
		msPKey = new MSPublicKey(publicKey);

	}
}
