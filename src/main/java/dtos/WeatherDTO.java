package dtos;

import entities.Weather;

import javax.persistence.EntityManagerFactory;

public class WeatherDTO {

    private String latitude;
    private String longitude;
    private Float tempInF;
    private Float tempInC;
    private String condition;


    public WeatherDTO(){
    }

    public WeatherDTO(Weather weather) {
        this.tempInC = weather.getTemp();
        this.condition = weather.getCondition();
    }

    public WeatherDTO(String latitude, String longitude, Float temp, String condition) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tempInF = temp;
        this.tempInC = (5* (temp - 32)) / 9;
        this.condition = condition;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Float getTempInF() {
        return tempInF;
    }

    public void setTempInF(Float tempInF) {
        this.tempInF = tempInF;
    }

    public Float getTempInC() {
        return tempInC;
    }

    public void setTempInC(Float tempInC) {
        this.tempInC = tempInC;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", tempInF=" + tempInF +
                ", tempInC=" + tempInC +
                ", condition='" + condition + '\'' +
                '}';
    }
}
