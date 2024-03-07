package naive.actors;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class ObserverActor extends ActorBasic24 {

	public ObserverActor(String name, ActorContext24 ctx) {
		super(name, ctx);
	}

	public boolean attach(ObservableActor observable) {
		return observable.addObserver(this);
	}

	public boolean detach(ObservableActor observable) {
		return observable.removeObserver(this);
	}

	@Override
	protected void elabMsg(IApplMessage msg) throws Exception {
		
		
		if (msg.isDispatch()) {
			// starting interaction
			if (msg.msgId().equals("cmd") && msg.msgContent().equals("start")) {
				IApplMessage message;
				
				if(this.name.equalsIgnoreCase("Observer_One")){
					message = CommUtils.buildRequest(name, "calc", "3", "MyObservable");
				}
				else if(this.name.equalsIgnoreCase("Observer_Two")) {
					message = CommUtils.buildRequest(name, "calc", "2", "MyObservable");
				}
				else {
					message = CommUtils.buildRequest(name, "calc", "ciao", "MyObservable");
				}
				
				request(message);
			}
			// received an update
			else if(msg.msgId().equals("update")) {
				CommUtils.outred(name + " 	| RECEIVED UPDATE from " + msg.msgSender() + " - " + msg.msgContent());
			}
		}
		// received response from previous request
		else if(msg.isReply()) {
			CommUtils.outmagenta(name + "	| Received result: " + msg.msgContent() + " from " + msg.msgSender());
		}
	}

}
