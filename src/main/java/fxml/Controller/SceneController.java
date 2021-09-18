package fxml.Controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import in.co.dreamsdoor.WeatherApp.Utils.WebAPICaller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author rajendraryadav
 */
public class SceneController implements Initializable {

    @FXML
    private ImageView backgroundImage;
    @FXML
    private Pane rightPane;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label currentDateLabel;
    @FXML
    private Label weatherStatus;
    @FXML
    private Label currentTemp;
    @FXML
    private Label weatherStatusLabel;
    @FXML
    private Label cityName;
    @FXML
    private Label cityFull;
    @FXML
    private Label pressure;

    private String temp;
    private WebAPICaller webAPICaller;

    public void getCurrentDate() {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM, d y");
        currentDateLabel.setText(dateFormat.format(currentDate));
        System.out.println("Current Date : " + dateFormat.format(currentDate));

    }

    public final Label getCurrentTimeLabel() {
        return currentTimeLabel;
    }

    public void TasksRunner() {
        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws Exception {

                while (true) {

                    Platform.runLater(() -> {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                        Date currentTime = new Date();
                        getCurrentTimeLabel()
                                .setText(dateFormat.format(currentTime));
                    });

                    Thread.sleep(1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void getCurrentTime() {
        TasksRunner();
    }

    public void setWeatherIcon() {
        Text icon;
        if ((new WebAPICaller()).getCurrentTempDouble() > 20 && (new WebAPICaller()).isDay()) {
            icon = GlyphsDude.createIcon(FontAwesomeIconName.CLOUD, "7.5em");

        } else {
            icon = GlyphsDude.createIcon(FontAwesomeIconName.SUN_ALT, "7.5em");
        }
        icon.setFill(Color.web("#ffe168"));
        weatherStatus.setGraphic(icon);
    }

    public void setTemp(String temp) {
        this.temp = temp;
        currentTemp.setText(this.temp);
    }

    public void setTempStatus(String status) {
        weatherStatusLabel.setText(status);
    }

    public String getTemp() {
        return this.temp;
    }

    public void setUICityName(String formattedCityName) {
        webAPICaller = new WebAPICaller();
        cityFull.setText(formattedCityName);
    }

    public void setUICityLatLong(String formaStringLatLong) {
        webAPICaller = new WebAPICaller();
        cityName.setText(formaStringLatLong);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webAPICaller = new WebAPICaller();

        try {
            webAPICaller.setCityName("Pune");
        } catch (MalformedURLException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        getCurrentDate();
        getCurrentTime();
        setUICityName(webAPICaller.getFormattedCityName());
        setUICityLatLong(webAPICaller.getLatLong());
        setTemp(webAPICaller.getCurrentTemp());
        setTempStatus(webAPICaller.getWeatherStatus());
        pressure.setText(webAPICaller.getPressure());
        setWeatherIcon();
    }

}
