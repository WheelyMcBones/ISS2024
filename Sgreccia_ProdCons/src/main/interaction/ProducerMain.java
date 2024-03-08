package main.interaction;

import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class ProducerMain {
//	private static String host = "localhost";
	private static String host = "130.136.113.239";
	private static String port = "8888";
	private static final int NUM_PRODS = 1;

	public static void main(String[] args) {
		
		CommUtils.outred("Starting Producers...");
		Producer[] prods = new Producer[NUM_PRODS];
		
		for(int i = 0; i < NUM_PRODS; i++) {
			prods[i] = new Producer("Produttor_" + i, ProtocolType.tcp, host, port);
			prods[i].activate();
		}
		
	}

}
