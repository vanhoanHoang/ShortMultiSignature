package oodrive.com.phd.MultiSignature.Parameters;

import it.unisa.dia.gas.jpbc.Element;

public class MSPrivateKey {
	private Element privateKey;

	public MSPrivateKey(Element privateKey) {
		this.privateKey = privateKey;
	}

	public Element getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(Element privateKey) {
		this.privateKey = privateKey;
	}

}
