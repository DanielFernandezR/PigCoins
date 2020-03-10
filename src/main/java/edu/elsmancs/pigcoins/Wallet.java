package edu.elsmancs.pigcoins;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		this.balance = getTotal_input() - getTotal_output();
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

	private void setInputTransactions(List<Transaction> InputTransactions) {
		this.inputTransactions = InputTransactions;
	}

	private void setOutputTransactions(List<Transaction> OutputTransactions) {
		this.outputTransactions = OutputTransactions;
	}

	@Override
	public String toString() {
		return "\nWallet = " + getAddress().hashCode() + "\n" + "Total input = " + getTotal_input() + "\n"
				+ "Total output = " + getTotal_output() + "\n" + "Balance = " + getBalance() + "\n";
	}

	public void loadCoins(BlockChain bChain) {
		double[] pigcoins = { 0d, 0d };
		pigcoins = bChain.loadWallet(getAddress());
		setTotal_input(pigcoins[0]);
		setTotal_output(pigcoins[1]);
		setBalance();
	}

	public void loadInputTransactions(BlockChain bChain) {
		setInputTransactions(bChain.loadInputTransactions(getAddress()));
	}

	public void loadOutputTransactions(BlockChain bChain) {
		setOutputTransactions(bChain.loadOutputTransactions(getAddress()));
	}

	public byte[] signTransaction(String message) {
		return GenSig.sign(getsKey(), message);
	}

	Map<String, Double> collectCoins(Double pigcoins) {
		if (getInputTransactions() == null) {
			return null;
		}

		if (pigcoins > getBalance()) {
			return null;
		}

		Set<String> consumedCoins = new HashSet<>();
		if (getOutputTransactions() != null) {
			for (Transaction transaction : getOutputTransactions()) {
				consumedCoins.add(transaction.getPrev_hash());
			}
		}

		Double contadorCoins = 0d;
		Map<String, Double> collectedCoins = new LinkedHashMap<String, Double>();
		for (Transaction transaccion : getInputTransactions()) {
			if (consumedCoins.contains(transaccion.getHash())) {
				continue;
			}

			if (pigcoins == transaccion.getPigcoins()) {
				collectedCoins.put(transaccion.getHash(), transaccion.getPigcoins());
				consumedCoins.add(transaccion.getHash());
				break;
			} else if (pigcoins < transaccion.getPigcoins()) {
				collectedCoins.put(transaccion.getHash(), pigcoins);
				collectedCoins.put("CA_" + transaccion.getHash(), transaccion.getPigcoins() - pigcoins);
				consumedCoins.add(transaccion.getHash());
				break;
			} else {
				collectedCoins.put(transaccion.getHash(), transaccion.getPigcoins());
				contadorCoins = transaccion.getPigcoins();
				pigcoins = pigcoins - contadorCoins;
				consumedCoins.add(transaccion.getHash());
			}
		}
		return collectedCoins;
	}

	public void sendCoins(PublicKey address, Double pigcoins, String message, BlockChain bChain) {

		Map<String, Double> consumedCoins = new LinkedHashMap<>();

		consumedCoins = collectCoins(pigcoins);

		if (consumedCoins != null) {
			bChain.processTransactions(getAddress(), address, consumedCoins, message, signTransaction(message));
		}

		this.loadCoins(bChain);
	}
}
