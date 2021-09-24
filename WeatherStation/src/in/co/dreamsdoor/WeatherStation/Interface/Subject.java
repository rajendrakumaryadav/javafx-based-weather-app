package in.co.dreamsdoor.WeatherStation.Interface;

public interface Subject {
    void registerObserver(Observerable observe);

    void removeObserver(Observerable observe);

    void notifyObservers();
}
