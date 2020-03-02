package edu.elsmancs.pigcoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;

import org.junit.Test;

public class WalletTest {

	BlockChain bloqueTransacciones;

	@Test
	public void ConstructorAndGeneratePairKeyTest() {
		Wallet monedero = new Wallet();
		KeyPair pair = GenSig.generateKeyPair();
		monedero.setSK(pair.getPrivate());
		monedero.setAddress(pair.getPublic());
		assertNotNull(monedero.getAddress());
	}

	@Test
	public void loadCoinsTest() {
		Wallet monedero = new Wallet();
		monedero.generateKeyPair();
		Wallet origin = new Wallet();
		origin.generateKeyPair();

		bloqueTransacciones = new BlockChain();
		Transaction primeraTrans = new Transaction("hash_1", "0", monedero.getAddress(), monedero.getAddress(), 20,
				"bacon eggs");
		bloqueTransacciones.addOrigin(primeraTrans);

		monedero.loadCoins(bloqueTransacciones);
		double delta = 0.00d;
		assertEquals(20, monedero.getTotal_input(), delta);
		assertEquals(20, monedero.getBalance(), delta);
	}
}
