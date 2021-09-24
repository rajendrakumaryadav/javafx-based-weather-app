package in.co.dreamsdoor.WeatherStation;

import in.co.dreamsdoor.WeatherStation.Interface.Observerable;
import in.co.dreamsdoor.WeatherStation.Interface.Subject;

import java.util.ArrayList;


public class WeatherData implements Subject {
    private final ArrayList<Observerable> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observerable observe) {
        observers.add(observe);
    }

    @Override
    public void removeObserver(Observerable observe) {
        int i = observers.indexOf(observe);
        if (i >= 0)
            observers.remove(i);
    }

    @Override
    public void notifyObservers() {
        for (Observerable observerable : observers) {
            observerable.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public float getPressure() {
        return this.pressure;
    }

    public float getHumidity() {
        return this.humidity;
    }


}
