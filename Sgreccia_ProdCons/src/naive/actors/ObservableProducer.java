package naive.actors;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class ObservableProducer extends ObservableActor {

	private int counterMessages = 0;
	private String dest = "Consumer";

	public ObservableProducer(String name, ActorContext24 ctx) {
		super(name, ctx);
	}

	@Override
	protected void elabMsg(IApplMessage msg) throws Exception {

		if (msg.isDispatch()) {
			// starting interaction
			if (msg.msgId().equals("cmd") && msg.msgContent().equals("start")) {
				IApplMessage message;
				
				CommUtils.outred(name + "	| Sending message to + " + this.dest + "--- SENDING UPDATES");
				message = CommUtils.buildRequest(name, "generic", "Hello_from_" + this.name + "_" + counterMessages, dest);
				updateResource(message.msgContent());
				request(message);
			}
		}
		// received response from previous request
		else if (msg.isReply()) {
			CommUtils.outmagenta(name + "	| Received result: " + msg.msgContent() + " from " + msg.msgSender());
			
			// triggering another request (test)
			if (counterMessages < 5) {
				counterMessages++;
				forward(CommUtils.buildDispatch(name, "cmd", "start", name));;
			}
		}
	}

}
