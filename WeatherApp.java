
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp extends JFrame {
    private JTextField locationField;
    private JTextField temperatureField;
    private JTextField humidityField;
    private JTextField conditionField;
    private JButton getWeatherButton;

    private static final String API_KEY = "Your_API_KEY";

    public WeatherApp() {
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Labels and Fields
        mainPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        mainPanel.add(locationField);

        mainPanel.add(new JLabel("Temperature:"));
        temperatureField = new JTextField();
        temperatureField.setEditable(false);
        mainPanel.add(temperatureField);

        mainPanel.add(new JLabel("Humidity:"));
        humidityField = new JTextField();
        humidityField.setEditable(false);
        mainPanel.add(humidityField);

        mainPanel.add(new JLabel("Condition:"));
        conditionField = new JTextField();
        conditionField.setEditable(false);
        mainPanel.add(conditionField);

        getWeatherButton = new JButton("Get Weather");
        mainPanel.add(getWeatherButton);

        add(mainPanel);

        pack();
    }

    private void setupListeners() {
        getWeatherButton.addActionListener(e -> fetchWeatherData());
    }

    private void fetchWeatherData() {
        String location = locationField.getText().trim();
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a location!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        new Thread(() -> {
            try {
                String weatherData = getWeatherData(location);
                parseAndDisplayWeather(weatherData);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error fetching weather data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }

    private String getWeatherData(String location) throws Exception {
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=metric";
        HttpURLConnection connection = (HttpURLConnection) new URL(urlStr).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("HTTP error code: " + connection.getResponseCode());
        }
    }

    private void parseAndDisplayWeather(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        double temperature = jsonObject.getJSONObject("main").getDouble("temp");
        int humidity = jsonObject.getJSONObject("main").getInt("humidity");
        String condition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

        SwingUtilities.invokeLater(() -> {
            temperatureField.setText(temperature + " °C");
            humidityField.setText(humidity + " %");
            conditionField.setText(condition);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WeatherApp().setVisible(true));
    }
}
