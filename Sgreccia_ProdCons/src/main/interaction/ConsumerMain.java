package main.interaction;

import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class ConsumerMain {
	
	public void configure() {
		Consumer consumer = new Consumer("El_Consumador", 8010, ProtocolType.tcp);
		CommUtils.outred("Starting Consumer: " + consumer.getName());
		consumer.startServerConsumer();	
	}

	public static void main(String[] args) {
		new ConsumerMain().configure();
	}

}
