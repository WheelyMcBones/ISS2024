package main;

import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class ConsumerMain {

	public static void main(String[] args) {
		Consumer consumer = new Consumer("El Consumador", 8010, ProtocolType.tcp);
		CommUtils.outred("Starting Consumer: " + consumer.getName());
		consumer.startServerConsumer();			
	}

}
