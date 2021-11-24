
public interface Subject {
    void subscribers(Observer observer);
    void unSubscribers(Observer observer);
    void notifyAllObserver();
}
