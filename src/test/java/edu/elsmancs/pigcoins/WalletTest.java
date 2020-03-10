package edu.elsmancs.pigcoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.KeyPair;
import java.util.Map;

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

	@Test
	public void collect_coins_test() {

		Wallet origin = new Wallet();
		origin.generateKeyPair();
		Wallet wallet = new Wallet();
		wallet.generateKeyPair();

		BlockChain bChain = new BlockChain();
		Transaction transaction = new Transaction("hash_1", "0", origin.getAddress(), wallet.getAddress(), 20,
				"a flying pig!");
		assertTrue(transaction.getHash().equals("hash_1"));
		bChain.addOrigin(transaction);
		transaction = new Transaction("hash_2", "1", origin.getAddress(), wallet.getAddress(), 10, "pig things!");
		assertTrue(transaction.getHash().equals("hash_2"));
		bChain.addOrigin(transaction);

		wallet.loadInputTransactions(bChain);
		assertTrue(wallet.getInputTransactions().size() == 2);
		assertTrue(wallet.getInputTransactions().get(0).getPigcoins() == 20);
		assertTrue(wallet.getInputTransactions().get(1).getPigcoins() == 10);

		wallet.loadCoins(bChain);
		assertEquals(30, wallet.getTotal_input(), 0);
		assertEquals(0, wallet.getTotal_output(), 0);
		assertEquals(30, wallet.getBalance(), 0);

		// la cantidad a enviar es exactamente la primera transaccion entrante
		Double pigcoins = 20d;
		Map<String, Double> coins = wallet.collectCoins(pigcoins);
		assertNotNull(coins);
		assertEquals(coins.size(), 1);
		assertEquals(20, coins.get("hash_1"), 0);

		// la cantidad a enviar es menor que la primera transaccion entrante

		wallet.loadInputTransactions(bChain);
		;
		pigcoins = 10.2d;
		coins = wallet.collectCoins(pigcoins);
		assertNotNull(coins);
		assertEquals(2, coins.size());
		assertEquals(10.2, coins.get("hash_1"), 0);
		assertEquals(9.8, coins.get("CA_hash_1"), 0);

		// la cantidad a enviar es mayor que la primera transaccion entrante

		wallet.loadInputTransactions(bChain);
		pigcoins = 25d;
		coins = wallet.collectCoins(pigcoins);
		assertNotNull(coins);
		assertTrue(coins.size() == 3);
		assertEquals(20, coins.get("hash_1"), 0);
		assertEquals(5, coins.get("hash_2"), 0);
		assertEquals(5, coins.get("CA_hash_2"), 0);

		// la cantidad a enviar es mayor que el balance = pigcoins disponibles en la
		// wallet
		wallet.loadInputTransactions(bChain);
		pigcoins = 40d;
		coins = wallet.collectCoins(pigcoins);
		assertNull(coins);

	}
}
