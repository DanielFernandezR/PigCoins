package edu.elsmancs.pigcoins;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {

	private PublicKey address = null;
	private PrivateKey sKey = null;
	private double total_input = 0d;
	private double total_output = 0d;
	private double balance = 0d;
	private List<Transaction> inputTransactions = new ArrayList<Transaction>();
	private List<Transaction> outputTransactions = new ArrayList<Transaction>();

	void generateKeyPair() {
		KeyPair pair = GenSig.generateKeyPair();
		this.setAddress(pair.getPublic());
		this.setSK(pair.getPrivate());
	}

	void setAddress(PublicKey address) {
		this.address = address;
	}

	void setSK(PrivateKey sKey) {
		this.sKey = sKey;
	}

	PublicKey getAddress() {
		return this.address;
	}

	private PrivateKey getsKey() {
		return this.sKey;
	}

	double getBalance() {
		return balance;
	}

	void setBalance() {
		if (getTotal_input() - getTotal_output() >= 0) {
			this.balance = getTotal_input() - getTotal_output();
		}
	}

	double getTotal_input() {
		return total_input;
	}

	private void setTotal_input(double pigcoins) {
		this.total_input = pigcoins;
	}

	double getTotal_output() {
		return total_output;
	}

	private void setTotal_output(double pigcoins) {
		this.total_output = pigcoins;
	}

	List<Transaction> getInputTransactions() {
		return inputTransactions;
	}

	List<Transaction> getOutputTransactions() {
		return outputTransactions;
	}

	@Override
	public String toString() {
		return "\nWallet = " + getAddress().hashCode() + "\n" + "Total input = " + getTotal_input() + "\n"
				+ "Total output = " + getTotal_output() + "\n" + "Balance = " + getBalance() + "\n";
	}

	public void loadCoins(BlockChain bChain) {
		Map<String, Double> pigcoins = bChain.loadWallet(getAddress());
		setTotal_input(pigcoins.get("input"));
		setTotal_output(pigcoins.get("output"));
		setBalance();
	}

	public void loadInputTransactions(BlockChain bChain) {
		for (Transaction transaccion : bChain.getBlockChain()) {
			if (transaccion.getpKey_recipient() == getAddress()) {
				getInputTransactions().add(transaccion);
			}
		}

	}

	public void loadOutputTransactions(BlockChain bChain) {
		for (Transaction transaccion : bChain.getBlockChain()) {
			if (transaccion.getpKey_sender() == getAddress()) {
				getOutputTransactions().add(transaccion);
			}
		}
	}

	public void sendCoins(PublicKey address, Double pigcoins, String message, BlockChain bChain) {
		collectCoins(pigcoins);
	}

	public byte[] signTransaction(String message) {
		byte[] mensajeFirmado = GenSig.sign(getsKey(), message);
		if (GenSig.verify(getAddress(), message, mensajeFirmado)) {
			return mensajeFirmado;
		} else {
			return null;
		}
	}

	Map<String, Double> collectCoins(Double pigcoins) {
		Map<String, Double> Coins = new HashMap<String, Double>();
		List<Transaction> inputTransactions = getInputTransactions();
		List<Transaction> outputTransactions = getOutputTransactions();
		Double contadorCoins = 0d;
		for (Transaction transaccion : inputTransactions) {
			if (pigcoins == transaccion.getPigcoins()) {
				Coins.clear();
				Coins.put(transaccion.getHash(), transaccion.getPigcoins());
				return Coins;
			} else if (pigcoins > transaccion.getPigcoins()) {
				Coins.put(transaccion.getHash(), transaccion.getPigcoins());
				if (contadorCoins == pigcoins) {
					return Coins;
				}
			} else {

			}
		}
		return Coins;
	}
}
