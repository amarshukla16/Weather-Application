# Weather-Application
# 🌦️ Java Swing Weather Application

A simple desktop Weather App built with **Java Swing** that fetches real-time weather data using the **OpenWeatherMap API**.

## ✨ Features

* Input any city name to get current weather data.
* Displays:

  * Temperature (in Celsius)
  * Humidity
  * Weather condition (e.g., cloudy, sunny)
* Responsive UI using Java Swing.
* Multithreaded for smooth UI performance during network calls.

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or higher
* Internet connection
* An [OpenWeatherMap API Key](https://openweathermap.org/api)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/amarshukla16/Weather-Application
   cd weather-app-java
   ```

2. **Replace the API key:**

   Open `WeatherApp.java` and replace the placeholder:

   ```java
   private static final String API_KEY = "Your_API_KEY";
   ```

   with your actual OpenWeatherMap API key.

3. **Compile and Run:**

   ```bash
   javac -cp .;json-<version>.jar WeatherApp.java
   java -cp .;json-<version>.jar WeatherApp
   ```

   *(Replace `json-<version>.jar` with your actual JSON library file)*

## 📦 Dependencies

* [org.json](https://mvnrepository.com/artifact/org.json/json) - For parsing JSON data from the API

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙌 Contributing

Feel free to fork this repository and submit pull requests! Suggestions and improvements are always welcome.

