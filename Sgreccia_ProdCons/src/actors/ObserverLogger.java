package actors;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class ObserverLogger extends ActorBasic24 {

	private PrintWriter logger;

	public ObserverLogger(String name, ActorContext24 ctx) {
		super(name, ctx);
		try {
			this.logger = new PrintWriter(new File("LogProdCons.txt"));
		} catch (FileNotFoundException e) {
			CommUtils.outred("Failed to create logger: " + e.getMessage());
		}
	}

	@Override
	protected void elabMsg(IApplMessage msg) throws Exception {
		// logs update messages
		if (msg.isDispatch() && msg.msgId().equals("update")) {
//			logger.append("LOG - Message from: " + msg.msgSender() + "	| " + msg.msgContent() + "\n");
			logger.append(msg.toString() + "\n");
			logger.flush();

			// utility per separare i singoli scambi di messaggi
//			if(msg.msgContent().startsWith("ACK")) {
//				logger.append("\n\n");
//			}
		}
	}

}
