package in.co.dreamsdoor.WeatherStation;

public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        var currentConditionDisplay
                = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
        // Update is notifying to display...
        currentConditionDisplay.update(72, 55, 39.2f);
        statisticsDisplay.update(72, 55, 39.2f);
        forecastDisplay.update(20, 22, 32.8f);

    }

}
