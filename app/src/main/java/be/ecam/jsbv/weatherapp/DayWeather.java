package be.ecam.jsbv.weatherapp;

/**
 * Created by 14309 on 07-02-17.
 */

public class DayWeather {
    private int dt;
    private Temp temp = null;
    private double pressure;
    private double humidity;
    private Weather weather = null;
    private double speed;
    private double deg;
    private double clouds;

    private class Temp {
        private double day;
        private double min;
        private double max;
        private double night;
        private double eve;
        private double morn;

        public Temp(double day, double min, double max,
             double night, double eve, double morn) {
            this.day = day;
            this.min = min;
            this.max = max;
            this.night = night;
            this.eve = eve;
            this.morn = morn;
        }
    }

    private class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }


    DayWeather(int dt, double pressure, double humidity,
               double speed, double deg, double clouds)
    {
        this.dt = dt;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
    }

    public void setTemp(double day, double min, double max,
                   double night, double eve, double morn){
        temp = new Temp(day, min, max, night, eve, morn);
    }

    public void setWeather(int id, String main, String description, String icon){
        weather = new Weather(id, main, description,icon);
    }

    public String getTemp() {
        return ("Temperatures: \n Day: " + temp.day
                + "\n Min: " + temp.min
                + "\n Max: " + temp.max
                + "\n Night: " + temp.night
                + "\n Evening: " + temp.eve
                + "\n Morning: " + temp.morn);
    }

    public String getWeather(){
        return (weather.id + ": " + weather.main + ". Description: " + weather.description);
    }

    public String getIcon() {
        return(weather.icon);
    }
}
