package edu.elsmancs.pigcoins;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TransactionTest {

	Transaction transaccion;
	Wallet monedero;

	@Test
	public void constructorYtoStringTest() {
		Wallet monedero = new Wallet();
		monedero.generateKeyPair();
		transaccion = new Transaction("hash_90", "08", monedero.getAddress(), monedero.getAddress(), 20, "Poggers");
		System.out.println(transaccion.toString());
		assertNotNull(monedero);
	}

}
