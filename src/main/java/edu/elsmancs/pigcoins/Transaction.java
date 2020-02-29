package edu.elsmancs.pigcoins;

import java.security.PublicKey;

public class Transaction {

	private String hash;
	private String prev_hash;
	private PublicKey pKey_sender = null;
	private PublicKey pKey_recipient = null;
	private double pigcoins = 0d;
	private String message = null;

	public Transaction() {
	}

	public Transaction(String hash, String prev_hash, PublicKey pKey_sender, PublicKey pKey_recipient, int pigcoins,
			String message) {

		this.hash = hash;
		this.prev_hash = prev_hash;
		this.pKey_sender = pKey_sender;
		this.pKey_recipient = pKey_recipient;
		this.pigcoins = pigcoins;
		this.message = message;
	}

	String getHash() {
		return hash;
	}

	String getPrev_hash() {
		return prev_hash;
	}

	PublicKey getpKey_sender() {
		return pKey_sender;
	}

	PublicKey getpKey_recipient() {
		return pKey_recipient;
	}

	double getPigcoins() {
		return pigcoins;
	}

	String getMessage() {
		return message;
	}

	public String toString() {
		return "\nhash = " + getHash() + "\n" + "prev_hash = " + getPrev_hash() + "\n" + "pKey_sender = "
				+ getpKey_sender().hashCode() + "\n" + "pKey_recipient = " + getpKey_recipient().hashCode() + "\n"
				+ "pigcoins = " + getPigcoins() + "\n" + "message = " + getMessage() + "\n";
	}

}
