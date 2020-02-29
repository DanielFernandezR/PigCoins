package edu.elsmancs.pigcoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {

	BlockChain bloqueTransacciones;
	Wallet monedero;

	@Before
	public void constructorYaddOriginTest() {
		bloqueTransacciones = new BlockChain();
		monedero = new Wallet();
		monedero.generateKeyPair();
		assertNotNull(bloqueTransacciones);
	}

	@Test
	public void addOriginTest() {
		Transaction primeraTrans = new Transaction("hash_1", "0", monedero.getAddress(), monedero.getAddress(), 20,
				"bacon eggs");
		bloqueTransacciones.addOrigin(primeraTrans);
		assertEquals(1, bloqueTransacciones.getBlockChain().size());
	}

	@Test
	public void summarizeTest() {
		Transaction primeraTrans = new Transaction("hash_1", "0", monedero.getAddress(), monedero.getAddress(), 40,
				"Poggers");
		Transaction segundaTrans = new Transaction("hash_2", "1", monedero.getAddress(), monedero.getAddress(), 210,
				"Que dius");
		bloqueTransacciones.addOrigin(primeraTrans);
		bloqueTransacciones.addOrigin(segundaTrans);
		bloqueTransacciones.summarize();
	}

	@Test
	public void summarizeSoloUnoTest() {
		Transaction primeraTrans = new Transaction("hash_1", "0", monedero.getAddress(), monedero.getAddress(), 40,
				"Poggers");
		Transaction segundaTrans = new Transaction("hash_2", "1", monedero.getAddress(), monedero.getAddress(), 210,
				"Que dius");
		Integer position = 1;
		bloqueTransacciones.addOrigin(primeraTrans);
		bloqueTransacciones.addOrigin(segundaTrans);
		bloqueTransacciones.summarize(position);
	}
}