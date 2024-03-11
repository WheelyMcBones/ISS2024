package actors;

import java.util.HashSet;

import java.util.Set;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public abstract class ObservableActor extends ActorBasic24 implements Observable {

	public ObservableActor(String name, ActorContext24 ctx) {
		super(name, ctx);
	}

	Set<ActorBasic24> observers = new HashSet<ActorBasic24>();

	/*
	 * Invia a tutti i gli Actor registrati presso di lui, come osservatori, un
	 * dispatch con msgId = "update"
	 */
	public void updateResource(String s) {
		// update all observers
		for (ActorBasic24 ob : observers) {
			this.forward(CommUtils.buildDispatch(this.name, "update", s, ob.getName()));
		}
	}

	// adding an observer to the observable, if not present
	public boolean addObserver(ActorBasic24 observer) {
		return this.observers.add(observer);
	}

	// removing an observer to the observable, if present
	public boolean removeObserver(ActorBasic24 observer) {
		return this.observers.remove(observer);
	}

	@Override
	protected abstract void elabMsg(IApplMessage msg) throws Exception;


}
