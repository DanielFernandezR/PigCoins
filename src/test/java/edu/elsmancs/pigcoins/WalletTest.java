package edu.elsmancs.pigcoins;

import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;

import org.junit.Test;

public class WalletTest {

	@Test
	public void ConstructorAndGeneratePairKeyTest() {
		Wallet monedero = new Wallet();
		KeyPair pair = GenSig.generateKeyPair();
		monedero.setSK(pair.getPrivate());
		monedero.setAddress(pair.getPublic());
		assertNotNull(monedero.getAddress());
	}

}
