package edu.elsmancs.pigcoins;

import java.util.ArrayList;

public class BlockChain {

	private ArrayList<Transaction> blockChain = new ArrayList<Transaction>();

	void addOrigin(Transaction transaccion) {
		blockChain.add(transaccion);
	}

	void summarize() {
		for (Transaction transaccion : blockChain) {
			System.out.println(transaccion);
		}
	}

	void summarize(Integer position) {
		System.out.println(blockChain.get(position));
	}

	// Métodos de testeo
	ArrayList<Transaction> getBlockChain() {
		return this.blockChain;
	}

}
