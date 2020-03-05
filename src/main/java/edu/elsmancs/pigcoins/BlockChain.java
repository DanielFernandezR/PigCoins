package edu.elsmancs.pigcoins;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	ArrayList<Transaction> getBlockChain() {
		return this.blockChain;
	}

	Map<String, Double> loadWallet(PublicKey address) {
		Map<String, Double> pigcoins = new HashMap<String, Double>();
		pigcoins.put("input", 0d);
		pigcoins.put("output", 0d);
		for (Transaction transaccion : getBlockChain()) {
			if (transaccion.getpKey_recipient() == address) {
				pigcoins.put("input", pigcoins.get("input") + transaccion.getPigcoins());
			} else if (transaccion.getpKey_sender() == address) {
				pigcoins.put("output", pigcoins.get("output") + transaccion.getPigcoins());
			}
		}
		return pigcoins;
	}
}
