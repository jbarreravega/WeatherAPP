package be.ecam.jsbv.weatherapp;

import android.view.ViewDebug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 14309 on 07-02-17.
 */

public class City {
    private int id = 0;
    private String name = null;
    private String country = null;
    private int population = 0;
    private double lon = 0;
    private double lat = 0;
    private int cod = 0;
    private double message = 0;
    private int cnt = 0;

    private static ArrayList<DayWeather> dayWeatherList = new ArrayList<>();
    private static ArrayList<City> cities = new ArrayList<>();

    public City (int id, String name, double lon, double lat,
                 String country, int population){
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.country = country;
        this.population = population;
    }

    public static void parse(String json) throws JSONException {
        JSONObject jsonCityWeather = new JSONObject(json);

        int cod = jsonCityWeather.getInt("cod");
        double message = jsonCityWeather.getDouble("message");
        int cnt = jsonCityWeather.getInt("cnt");

        JSONObject jsonCity = jsonCityWeather.getJSONObject("city");
        int id = jsonCity.getInt("id");
        String name = jsonCity.getString("name");
        double lon = (jsonCity.getJSONObject("coord")).getDouble("lon");
        double lat = (jsonCity.getJSONObject("coord")).getDouble("lat");
        String country = jsonCity.getString("country");
        int population = jsonCity.getInt("population");
        City city = new City(id, name, lon, lat, country, population);

        cities.add(city);


        JSONArray jsonDayWeatherList = jsonCityWeather.getJSONArray("list");
        for(int i = 0; i < cnt; i++) {
            JSONObject jsonDayWeather = jsonDayWeatherList.getJSONObject(i);
            int dt = jsonDayWeather.getInt("dt");
            double pressure = jsonDayWeather.getDouble("pressure");
            double humidity = jsonDayWeather.getDouble("humidity");
            double speed = jsonDayWeather.getDouble("speed");
            double deg = jsonDayWeather.getDouble("deg");
            double clouds = jsonDayWeather.getDouble("clouds");

            JSONObject jsonTemp = jsonDayWeather.getJSONObject("temp");
            double day = jsonTemp.getDouble("day");
            double min = jsonTemp.getDouble("min");
            double max = jsonTemp.getDouble("max");
            double night = jsonTemp.getDouble("night");
            double eve = jsonTemp.getDouble("eve");
            double morn = jsonTemp.getDouble("morn");

            //JSONObject jsonWeather = jsonDayWeather.getJSONObject("weather");
            //int weatherID = jsonWeather.getInt("id");
            //String main = jsonWeather.getString("main");
            //String description = jsonWeather.getString("description");
            //String icon = jsonWeather.getString("icon");

            DayWeather dayWeather = new DayWeather(dt, pressure, humidity, speed, deg, clouds);
            dayWeather.setTemp(day, min, max, night, eve, morn);
            //dayWeather.setWeather(id, main, description, icon);

            dayWeatherList.add(dayWeather);
        }
    }

    public int getID(){
        return id;
    }

    public static String[] getNames() {
        String[] names = new String[cities.size()];
        for (int i=0; i<cities.size(); i++) {
            names[i] = cities.get(i).name;
        }

        return names;
    }

    public String getCountry(){
        return country;
    }

    public int getPopulation(){
        return population;
    }

    public String getCoordinatesAsString()
    {
        return ("lon: " + lon + ", lat: "+ lat);
    }
}
