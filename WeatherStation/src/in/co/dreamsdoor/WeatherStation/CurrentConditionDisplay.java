package in.co.dreamsdoor.WeatherStation;

import in.co.dreamsdoor.WeatherStation.Interface.DisplayElement;
import in.co.dreamsdoor.WeatherStation.Interface.Observerable;
import in.co.dreamsdoor.WeatherStation.Interface.Subject;

public class CurrentConditionDisplay implements Observerable, DisplayElement {
    private final Subject weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current Condition : " + temperature + '\u2109'
                + " and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
