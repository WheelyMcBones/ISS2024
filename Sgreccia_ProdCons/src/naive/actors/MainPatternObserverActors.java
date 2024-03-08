package naive.actors;

import unibo.basicomm23.utils.CommUtils;

public class MainPatternObserverActors {

	public void configureTheSystem() {

		int port1 = 8123;
		CommUtils.outblue("Main - CREAZIONE CONTESTI ");
		ActorContext24 ctx1 = new ActorContext24("ctx1", "localhost", port1);
		CommUtils.outblue("Main - CREAZIONE ATTORI ");

		ObservableConsumer consumer = new ObservableConsumer("Consumer", ctx1);
		ObservableProducer producer1 = new ObservableProducer("Prod_One", ctx1);
		
//		AlienJava alien = new AlienJava("Alien", ProtocolType.tcp, "localhost", Integer.toString(port1));

		ObserverLogger logger = new ObserverLogger("Logger", ctx1);
		consumer.addObserver(logger);
		producer1.addObserver(logger);


		ctx1.showActorNames();
		logger.activateAndStart();

		consumer.activateAndStart();
//		alien.activate();
		producer1.activateAndStart();


	}

	public static void main(String[] args) {
		new MainPatternObserverActors().configureTheSystem();
	}
}
