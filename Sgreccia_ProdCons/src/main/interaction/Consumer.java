package main.interaction;

import unibo.basicomm23.enablers.ServerFactory;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.IApplMsgHandler;
import unibo.basicomm23.interfaces.IServer;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

// Implementa IApplMsgHandler -> gestisce messaggi in arrivo
public class Consumer implements IApplMsgHandler {
//	private final String hostAddr;
	private int port;
	private ProtocolType protocol;
//	private Interaction connection;
	private IServer server;
	
	public Consumer(String consumerName, int port, ProtocolType protocol) {
		this.consumerName = consumerName;
		this.port = port;
		this.protocol = protocol;
	}

	private String consumerName;

	// crea il TCPServer (con ServerSocket) -> utilizza un ConnectionHandler per la gestione della connessione bidirezionale
	
	protected void startServerConsumer() {
		server = ServerFactory.create(this.getName(), port, protocol, this);
		server.start();
	}

	@Override
	public String getName() {
		return consumerName;
	}

	@Override
	// Elabora il messaggio: distinguiamo se Request o Forward
	public void elaborate(IApplMessage message, Interaction conn) {
		this.status(message);

		// è una REQUEST -> Producer si aspetta una risposta (ACK)
		if (message.isRequest()) {
			String payload = "ACK_inResponseTo_" + message.msgContent() + "_from_" + message.msgSender();

			// costruisco risposta (ACK)
			IApplMessage response = CommUtils.buildReply(this.getName(), message.msgId(), payload, message.msgSender());
			System.out.println("RESPONSE FORMAT: " + response + "SeqNUM: " + response.msgNum());

			// invio risposta
			try {
				conn.reply(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Consumer " + this.getName() + ": Error on " + response.msgContent());
			}
			CommUtils.outblue("Consumer " + this.getName() + ": sended response '" + response.msgContent() + "'");

		}
		// è una FORWARD -> Producer NON si aspetta una risposta
		else if (message.isDispatch()) {
			CommUtils.outmagenta("Consumer " + this.getName() + ": consumed message.");
		}

	}

	// Stampa
	private void status(IApplMessage msg) {
		String type = (msg.isRequest()) ? "-> Sending Response..." : "-> Done";
		String stat = "Consumer " + this.getName() + ": received message: '" + msg.msgContent() + "' from Producer "
				+ msg.msgSender() + " " + type;
		if (msg.isRequest()) {
			CommUtils.outblue(stat);
		} else if (msg.isDispatch()) {
			CommUtils.outmagenta(stat);

		}
	}

}
