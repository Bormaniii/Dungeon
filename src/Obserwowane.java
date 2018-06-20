import java.util.ArrayList;
import java.util.List;

public class Obserwowane {

	private List<Observer> observers = new ArrayList<Observer>();
	private int state;
	public void addObserver(Observer Observer) {
		observers.add(Observer);
	}
	public void deleteObserver(Observer Observer) {
		observers.remove(Observer);		
	}
	public void notifyObservers() {
		for(Observer Observer: observers) {
			Observer.update();
		}
	}
	public void notifyObserver(Observer ob) {
		ob.update();
	}
}
