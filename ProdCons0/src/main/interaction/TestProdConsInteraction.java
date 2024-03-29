package main.interaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

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
public class TestProdConsInteraction {
	private static Interaction connSupport;

	@BeforeClass
	public static void activateConsumer() {
		CommUtils.outmagenta("activateConsumer");
		new MainEmablersConsumerOnly().configureTheSystem();
		connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8888");
	}

	@After
	public void down() {
		CommUtils.outmagenta("end of  a test ");
	}

//	@Test
//	public void testRequest() {
//		CommUtils.outmagenta("testRequest ======================================= ");
//		// Funge da Producer come ProducerUsingConnection
//		IApplMessage req = CommUtils.buildRequest("Sgreccia", "distance", "distance(20)", "consumer");
//		IApplMessage req1 = CommUtils.buildRequest("Sgreccia", "distance", "distance(30)", "consumer");
//		try {
//			IApplMessage reply = connSupport.request(req);
//			CommUtils.outblue("reply=" + reply);
//			String answer = reply.msgContent();
//			assertEquals(answer, "ack(distance(20))");
//
//			IApplMessage reply1 = connSupport.request(req1);
//			CommUtils.outblue("reply1=" + reply1);
//			String answer1 = reply1.msgContent();
//			assertEquals(answer1, "ack(distance(30))");
//
//		} catch (Exception e) {
//			fail("testRequest " + e.getMessage());
//		}
//	}

	@Test
	public void testRequestAfterRequestFail() {
		CommUtils.outmagenta("testRequestAfterRequestFail ======================================= ");
		// Funge da Producer come ProducerUsingConnection
		IApplMessage req = CommUtils.buildRequest("sgreccia", "distance", "distance(20)", "consumer");
		IApplMessage req1 = CommUtils.buildRequest("sgreccia", "distance", "distance(190)", "consumer");
		try {
			connSupport.forward(req);

			IApplMessage reply1 = connSupport.request(req1);
			CommUtils.outblue("reply1=" + reply1);
			String answer1 = reply1.msgContent();
//			assertEquals(answer1, "ack(distance(190))");
			assertNotSame(answer1, "ack(distance(190))");
			connSupport.receive(); 

		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
		}
	}
	
	@Test
	public void testRequestAfterRequestOk() {
		CommUtils.outmagenta("testRequestAfterRequestOk ======================================= ");
		// Funge da Producer come ProducerUsingConnection
		IApplMessage req = CommUtils.buildRequest("sgreccia", "distance", "distance(20)", "consumer");
		IApplMessage req1 = CommUtils.buildRequest("sgreccia", "distance", "distance(150)", "consumer");
		IApplMessage req2 = CommUtils.buildRequest("sgreccia", "distance", "distance(190)", "consumer");
		IApplMessage req3 = CommUtils.buildRequest("sgreccia", "distance", "distance(210)", "consumer");
		IApplMessage req4 = CommUtils.buildRequest("sgreccia", "distance", "distance(250)", "consumer");
		try {

			
			connSupport.forward(req);
			connSupport.forward(req1);
			connSupport.forward(req2);
			connSupport.forward(req3);
			connSupport.forward(req4);
			
			IApplMessage reply = connSupport.receive();
			IApplMessage reply1 = connSupport.receive();
			IApplMessage reply2 = connSupport.receive();
			IApplMessage reply3 = connSupport.receive();
			IApplMessage reply4 = connSupport.receive();

			CommUtils.outblue("reply=" + reply);
			CommUtils.outblue("reply1=" + reply1);
			CommUtils.outblue("reply2=" + reply2);
			CommUtils.outblue("reply3=" + reply3);
			CommUtils.outblue("reply4=" + reply4);

			String answer = reply.msgContent();
			String answer1 = reply1.msgContent();
			String answer2 = reply2.msgContent();
			String answer3 = reply3.msgContent();
			String answer4 = reply4.msgContent();
			
			
			assertEquals(answer, "ack(distance(20))");
			assertEquals(answer1, "ack(distance(150))");
			assertEquals(answer2, "ack(distance(190))");
			assertEquals(answer3, "ack(distance(210))");
			assertEquals(answer4, "ack(distance(250))");

		
		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
		}
	}

//	@Test
//	public void testDispatch() {
//		CommUtils.outmagenta("testDispatch ======================================= ");
//		// Funge da Producer come ProducerUsingConnection
//		IApplMessage msg = CommUtils.buildDispatch("Sgreccia", "distance", "distance(20)", "consumer");
//		IApplMessage msg1 = CommUtils.buildDispatch("Sgreccia", "distance", "distance(30)", "consumer");
//		try {
//			connSupport.forward(msg);
//			connSupport.forward(msg1);
//			// CommUtils.delay(500);
//			readLogFile();
//		} catch (Exception e) {
//			fail("testRequest " + e.getMessage());
//			e.printStackTrace();
//		}
//	}

	protected void readLogFile() throws IOException {
		String line;
		IApplMessage m;
		File myObj = new File("TestLog.txt");
		Scanner myReader = new Scanner(myObj);
		line = myReader.nextLine();
		m = new ApplMessage(line);
		CommUtils.outblue("" + m);
		assertEquals(m.msgContent(), "distance(20)");
		line = myReader.nextLine();
		m = new ApplMessage(line);
		CommUtils.outblue("" + m);
		assertEquals(m.msgContent(), "distance(30)");
		myReader.close();
	}
}
