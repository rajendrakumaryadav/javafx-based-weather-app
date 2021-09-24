package in.co.dreamsdoor.WeatherStation;

import in.co.dreamsdoor.WeatherStation.Interface.DisplayElement;
import in.co.dreamsdoor.WeatherStation.Interface.Observerable;

/**
 * in.co.dreamsdoor.WeatherStation.StatisticsDisplay for the all data that has been gathered.
 *
 * @author rajendrayadav
 * @see in.co.dreamsdoor.WeatherStation.Interface.DisplayElement
 * @see in.co.dreamsdoor.WeatherStation.Interface.Observerable
 */
public class StatisticsDisplay implements Observerable, DisplayElement {
    private final WeatherData weatherData;
    private float minTemp = 200.0f;
    private float maxTemp = 0.0f;
    private float tempSum = 0.0f;
    private int numReadings;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Avg/Min/Max - Temperature " + (tempSum / numReadings)
                + "/" + maxTemp + "/" + minTemp);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        tempSum += temp;
        numReadings += 1;

        if (temp > maxTemp)
            maxTemp = temp;

        if (temp < minTemp)
            minTemp = temp;

        display();
    }
}
