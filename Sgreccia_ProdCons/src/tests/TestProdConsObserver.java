package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.ws.WsConnSysObserver;
import naive.actors.*;

/*
 * ===========================================================================
 * Test che 
 *    - attiva il consumatore (una volta sola)
 *    - esegue il test di invio di una richiesta (testRequest)
 *      osservabilità data dalla risposta
 *    - esegue il test di invio di due dispatch (testDispatch) con 
 *      osservabilità data da file di log
 * ===========================================================================
 */
public class TestProdConsObserver {

	static MainPatternObserverActors main = null;

	@BeforeClass
	public static void activateConsumer() {
//		int port1 = 8123;
//		CommUtils.outblue("Main - CREAZIONE CONTESTI ");
//		ActorContext24 ctx1 = new ActorContext24("ctx1", "localhost", port1);
//		CommUtils.outblue("Main - CREAZIONE ATTORI ");
//
//		ObservableConsumer consumer = new ObservableConsumer("Consumer", ctx1);
//		ObservableProducer producer1 = new ObservableProducer("Prod_One", ctx1);
//		ObserverLogger logger = new ObserverLogger("Logger", ctx1);
//		consumer.addObserver(logger);
//		producer1.addObserver(logger);

		main = new MainPatternObserverActors();
		main.configureTheSystem();

	}

	@After
	public void down() {
		CommUtils.outmagenta("end of  a test ");
	}

	@Test
	public void testObserversLog() {
		CommUtils.outmagenta("testObserversLog ======================================= ");

		try {
			Thread.sleep(3000); // waits for end of messages
			readLogFile();
		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
		}
	}

	protected void readLogFile() throws IOException {
		String line, dest;
		IApplMessage message;
		File myObj = new File("LogProdCons.txt");
		BufferedReader myReader = new BufferedReader(new FileReader(myObj));

		while ((line = myReader.readLine()) != null) {
			message = new ApplMessage(line);
			CommUtils.outblue("" + message);

			// messaggio da parte del produttore
			if (message.msgSender().startsWith("prod")) {
				// incremento i messaggi "pending" per quel produttore
				MainPatternObserverActors.producerAndMess.put(message.msgSender(),
						MainPatternObserverActors.producerAndMess.get(message.msgSender()) + 1);
			}
			// messaggio da parte del consumatore
			else {
				// ricaviamo dal messaggio il Produttore corrispondente
				dest = message.msgContent().split("from_")[1];
				dest = dest.replace(")", "");

				// decremento i messaggi "pending"
				MainPatternObserverActors.producerAndMess.put(dest,
						MainPatternObserverActors.producerAndMess.get(dest) - 1);

			}

		}

		// stampe di controllo e test
		for (String nameProd : MainPatternObserverActors.producerAndMess.keySet()) {
			int n = MainPatternObserverActors.producerAndMess.get(nameProd);
			System.out.println("Prod: " + nameProd + " has " + n + " pending");
			assertEquals(MainPatternObserverActors.producerAndMess.get(nameProd).intValue(), 0);
		}

		myReader.close();
	}
}
