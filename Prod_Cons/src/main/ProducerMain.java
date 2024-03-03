package main;

import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class ProducerMain {
	private static String host = "localhost";
	private static String port = "8010";

	public static void main(String[] args) {
		Producer prod = new Producer("Le_Produttor", ProtocolType.tcp, host, port);
		CommUtils.outred("Starting Producer...");
		prod.activate();
	}

}
