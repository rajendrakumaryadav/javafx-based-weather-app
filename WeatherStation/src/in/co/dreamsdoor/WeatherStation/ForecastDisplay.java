package in.co.dreamsdoor.WeatherStation;

import in.co.dreamsdoor.WeatherStation.Interface.DisplayElement;
import in.co.dreamsdoor.WeatherStation.Interface.Observerable;

public class ForecastDisplay implements Observerable, DisplayElement {
    private final WeatherData weatherData;
    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Forecast : ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the time!");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather!");
        }
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
}
