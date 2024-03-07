package naive.actors;

import java.util.HashSet;

import java.util.Set;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class ObservableActor extends ActorBasic24 {

	public ObservableActor(String name, ActorContext24 ctx) {
		super(name, ctx);
	}

	Set<ObserverActor> observers = new HashSet<ObserverActor>();

	/*
	 * Invia a tutti i gli Actor registrati presso di lui, come osservatori, un
	 * dispatch con msgId = "update"
	 */
	protected void updateResource(String s) {
		// update all observers
		for (ObserverActor ob : observers) {
			this.forward(CommUtils.buildDispatch(this.name, "update", s, ob.getName()));
		}
	}
	
	// adding an observer to the observable, if not present
	protected boolean addObserver(ObserverActor observer) {
		return this.observers.add(observer);
	}
	
	// removing an observer to the observable, if present
	protected boolean removeObserver(ObserverActor observer) {
		return this.observers.remove(observer);
	}

	@Override
	/*
	 * Elaborazione messaggi: supponiamo che l'attore restituisca il quadrato del
	 * numero arrivato: se il messaggio non contiene un numero, si procede ad aggiornare gli observers
	 */
	protected void elabMsg(IApplMessage msg) throws Exception {
		CommUtils.outblue(name+ "	| Received " + msg.msgType() +  " : " + msg.msgContent() + " from " + msg.msgSender() + " - ID: " + msg.msgId());

		double ris =  -1;
		if (msg.isRequest()) {
			try {
				ris = Math.pow(Integer.parseInt(msg.msgContent()), 2);
			} catch (NumberFormatException e) {
				CommUtils.outred(name+ "	| Found Error from + " + msg.msgSender() + "--- SENDING UPDATES");
				updateResource("BadFormatted_Number_from_" + msg.msgSender());
				return;
			}
			
			this.reply(msg, CommUtils.buildReply(this.name, msg.msgId(), Double.toString(ris), msg.msgSender()));
		}
		

	}

}
