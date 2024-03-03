package main;

import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class ProducerMain {
	private static String host = "localhost";
	private static String port = "8010";
	private static final int NUM_PRODS = 3;

	public static void main(String[] args) {
		
		CommUtils.outred("Starting Producers...");
		Producer[] prods = new Producer[NUM_PRODS];
		
		for(int i = 0; i < NUM_PRODS; i++) {
			prods[i] = new Producer("Produttor_" + i, ProtocolType.tcp, host, port);
			prods[i].activate();
		}
		
	}

}
