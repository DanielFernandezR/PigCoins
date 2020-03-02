package edu.elsmancs.pigcoins;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {

	private PublicKey address = null;
	private PrivateKey sKey = null;
	private double total_input = 0d;
	private double total_output = 0d;
	private double balance = 0d;

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
		this.total_input += pigcoins;
	}

	double getTotal_output() {
		return total_output;
	}

	private void setTotal_output(double pigcoins) {
		this.total_output += pigcoins;
	}

	@Override
	public String toString() {
		return "\nWallet = " + getAddress().hashCode() + "\n" + "Total input = " + getTotal_input() + "\n"
				+ "Total output = " + getTotal_output() + "\n" + "Balance = " + getBalance() + "\n";
	}

	public void loadCoins(BlockChain bChain) {
		for (Transaction transaccion : bChain.getBlockChain()) {
			if (transaccion.getpKey_recipient() == getAddress()) {
				setTotal_input(transaccion.getPigcoins());
			} else if (transaccion.getpKey_sender() == getAddress()) {
				setTotal_output(transaccion.getPigcoins());
			}
		}
		setBalance();
	}
}
