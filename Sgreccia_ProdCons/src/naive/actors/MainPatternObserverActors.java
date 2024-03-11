package naive.actors;

import java.util.HashMap;
import java.util.Map;

import unibo.basicomm23.utils.CommUtils;

public class MainPatternObserverActors {
	public static final int NUM_PRODS = 3;
	public static Map<String, Integer> producerAndMess = new HashMap<>(NUM_PRODS);

	public void configureTheSystem() {

		int port1 = 8123;
		CommUtils.outblue("Main - CREAZIONE CONTESTI ");
		ActorContext24 ctx1 = new ActorContext24("ctx1", "localhost", port1);
		CommUtils.outblue("Main - CREAZIONE ATTORI ");

		ObservableConsumer consumer = new ObservableConsumer("Consumer", ctx1);
		ObservableProducer[] producers = new ObservableProducer[NUM_PRODS];
		for (int i = 0; i < NUM_PRODS; i++) {
			producers[i] = new ObservableProducer("prod" + i, ctx1);
		}

//		AlienJava alien = new AlienJava("Alien", ProtocolType.tcp, "localhost", Integer.toString(port1));

		ObserverLogger logger = new ObserverLogger("Logger", ctx1);
		consumer.addObserver(logger);
		for (int i = 0; i < NUM_PRODS; i++) {
			producers[i].addObserver(logger);
			MainPatternObserverActors.producerAndMess.put(producers[i].name, 0);
		}
		
		

		ctx1.showActorNames();
		logger.activateAndStart();

		consumer.activateAndStart();
//		alien.activate();
		for (int i = 0; i < NUM_PRODS; i++) {
			producers[i].activateAndStart();
		}

	}

	public static void main(String[] args) {
		new MainPatternObserverActors().configureTheSystem();
	}
}
