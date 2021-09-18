package in.co.dreamsdoor.WeatherApp.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class WebAPICaller {

    private final String KEY = System.getenv("WEATHER_MAP_API_KEY");
    private String data = null;
    private String cityName = null;
    private static JSONObject jsonData = null;

    /**
     * @author Rajendra Kumar R Yadav
     * @throws MalformedURLException
     * @serialData 20210921
     * @see MalformedURLException
     * @since 1.0
     */
    private void startAPICall() throws MalformedURLException {
        if (KEY == null) {
            System.out.println("Failed to get ENV variable.");
            System.exit(-1);
        }

        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=";
        URL url = new URL(urlString + getCityName() + "&appid=" + KEY + "&units=metric");

        try {
            URLConnection uRLConnection = url.openConnection();
            uRLConnection.setRequestProperty("Content-Type", "application/json");
            uRLConnection.setDoInput(true);
            uRLConnection.setAllowUserInteraction(false);
            BufferedReader br = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
            String l = null;
            while ((l = br.readLine()) != null) {
                WebAPICaller.jsonData = new JSONObject(l);
            }
            br.close();

        } catch (Exception e) {
            System.out.printf("Message : %s\nCause : %s\n ", e.getMessage(), e.getCause());
            System.exit(-1);
        }
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    private JSONObject getJSONData() {
        return WebAPICaller.jsonData;
    }

    public void setCityName(String name) throws MalformedURLException {
        this.cityName = name;
        this.startAPICall();

    }

    public String getCityName() {
        return cityName;
    }

    public String getFormattedCityName() {
        return getJSONData().getString("name") + ", " + getJSONData().
                getJSONObject("sys").getString("country");
    }

    public String getLatLong() {
        return getJSONData().getJSONObject("coord").getDouble("lon") + ", "
                + getJSONData().getJSONObject("coord").getDouble("lat");
    }

    public Double getCurrentTempDouble() {
        return getJSONData().getJSONObject("main").getDouble("temp");
    }

    public String getCurrentTemp() {
        return getJSONData().getJSONObject("main").getDouble("temp") + " " + "°C";
    }

    public String getWeatherStatus() {
        return getJSONData().getJSONArray("weather").getJSONObject(0).getString("main");
    }

    public String getPressure() {
        return getJSONData().getJSONObject("main").getInt("pressure") + " hPa";
    }

    public boolean isDay() {
        return true;
    }


}

