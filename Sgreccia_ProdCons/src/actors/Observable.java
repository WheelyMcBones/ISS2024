package actors;

public interface Observable {
	public void updateResource(String s);

	public boolean addObserver(ActorBasic24 observer);

	public boolean removeObserver(ActorBasic24 observer);
}
