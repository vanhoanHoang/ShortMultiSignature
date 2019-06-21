package oodrive.com.phd.MultiSignature.Parameters;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class SystemParameters {
	public Pairing pairing;
	public Element g; // generator

	public SystemParameters() {
		TypeACurveGenerator generator = new TypeACurveGenerator(160, 512);
		PairingParameters pairingParams = generator.generate();
		this.pairing = PairingFactory.getPairing(pairingParams);
		g = this.pairing.getG1().newRandomElement();

	}

	public Pairing getPairing() {
		return this.pairing;
	}

	public Element getGenerator() {
		return g;
	}

}
