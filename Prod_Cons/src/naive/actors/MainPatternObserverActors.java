package naive.actors;

import unibo.basicomm23.utils.CommUtils;

public class MainPatternObserverActors {

	public void configureTheSystem() {

		int port1 = 8123;
		CommUtils.outblue("Main - CREAZIONE CONTESTI ");
		ActorContext24 ctx1 = new ActorContext24("ctx1", "localhost", port1);
		CommUtils.outblue("Main - CREAZIONE ATTORI ");

		ObservableActor observable = new ObservableActor("MyObservable", ctx1);
		ObserverActor obs1 = new ObserverActor("Observer_One", ctx1);
		ObserverActor obs2 = new ObserverActor("Observer_Two", ctx1);
		ObserverActor obs3 = new ObserverActor("Observer_Three", ctx1);

		obs1.attach(observable);
		obs2.attach(observable);
		obs3.attach(observable);

		ctx1.showActorNames();

		observable.activateAndStart();
		obs1.activateAndStart();
		obs2.activateAndStart();
		obs3.activateAndStart();
}

	public static void main(String[] args) {
		new MainPatternObserverActors().configureTheSystem();
	}
}
