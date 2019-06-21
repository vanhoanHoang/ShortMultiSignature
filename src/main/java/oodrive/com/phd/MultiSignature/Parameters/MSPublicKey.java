package oodrive.com.phd.MultiSignature.Parameters;

import it.unisa.dia.gas.jpbc.Element;

public class MSPublicKey {
	public Element publicKey;
	
	public MSPublicKey(Element publicKey) {
		this.publicKey = publicKey;
	}
	
	public Element getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(Element publicKey) {
		this.publicKey = publicKey;
	}
	
	
	
}
