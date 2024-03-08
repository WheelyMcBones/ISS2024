package notUsed;

import naive.actors.ActorBasic24;
import naive.actors.ActorContext24;
import naive.actors.ObservableActor;
import unibo.basicomm23.interfaces.IApplMessage;

public abstract class ObserverActor extends ActorBasic24 {

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
	protected abstract void elabMsg(IApplMessage msg) throws Exception;


}
