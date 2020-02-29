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

	void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "\nWallet = " + getAddress().hashCode() + "\n" + "Total input = " + total_input + "\n"
				+ "Total output = " + total_output + "\n" + "Balance = " + getBalance() + "\n";
	}
}
