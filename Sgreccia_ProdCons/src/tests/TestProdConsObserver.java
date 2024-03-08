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
		
		new MainPatternObserverActors().configureTheSystem();
	
	}

	@After
	public void down() {
		CommUtils.outmagenta("end of  a test ");
	}


	@Test
	public void testObserversLog() {
		CommUtils.outmagenta("testObserversLog ======================================= ");

		try {
			Thread.sleep(800); // waits for end of messages
			readLogFile();
		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
		}
	}

	protected void readLogFile() throws IOException {
		String line;
		IApplMessage messageFromProd, responseFromConsumer;
		File myObj = new File("LogProdCons.txt");
		BufferedReader myReader = new BufferedReader(new FileReader(myObj));
		
		// producer sends 5 messages --> testing all 5 exchanges between Producer and Consumer
		int i = 0;

		while (myReader.readLine() != null) {
			line = myReader.readLine();
			messageFromProd = new ApplMessage(line);
			CommUtils.outblue("" + messageFromProd);

			assertEquals(messageFromProd.msgContent(), "Hello_from_Prod_One_" + i);

			myReader.readLine(); // not used
			line = myReader.readLine();

			responseFromConsumer = new ApplMessage(line);
			CommUtils.outblue("" + responseFromConsumer);
			assertEquals(responseFromConsumer.msgContent(), "ack(" + messageFromProd.msgContent() + ")");
			
			i++;
		}

		myReader.close();
	}
}
