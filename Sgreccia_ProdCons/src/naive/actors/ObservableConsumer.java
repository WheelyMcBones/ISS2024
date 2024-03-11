package naive.actors;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class ObservableConsumer extends ObservableActor {

	public ObservableConsumer(String name, ActorContext24 ctx) {
		super(name, ctx);
	}

	@Override
	protected void elabMsg(IApplMessage msg) throws Exception {

		if (msg.isRequest() && !msg.msgSender().equalsIgnoreCase("Alien")) {
			CommUtils.outred(name + "	| Received message from + " + msg.msgSender() + "--- SENDING UPDATES");

			IApplMessage reply = CommUtils.buildReply(this.name, msg.msgId(),
					"ack(" + msg.msgContent() + ")", msg.msgSender());
			this.reply(msg, reply);

			updateResource(reply.msgContent());

		} else
			CommUtils.outgray(name + "	| Received message from + " + msg.msgSender() + "--- No Updates");

	}

}
