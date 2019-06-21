package oodrive.com.phd.MultiSignature.Parameters;

import it.unisa.dia.gas.jpbc.Element;

public class MSSignature {

	public Element alpha;
	
	public MSSignature(Element alpha) {
		this.alpha = alpha; 
	}
		
	public Element getAlpha() {
		return alpha;
	}

	public void setAlpha(Element alpha) {
		this.alpha = alpha;
	}
	
	
}
