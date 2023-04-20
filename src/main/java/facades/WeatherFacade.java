package facades;

import com.google.gson.Gson;
import dtos.*;
import entities.City;
import entities.Weather;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherFacade {

    private static WeatherFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private WeatherFacade(){}

    // Method returns an instance of the FacadeExample class
    public static WeatherFacade getWeatherFacade(EntityManagerFactory entityManagerFactory1){
        if( instance == null) {
            entityManagerFactory = entityManagerFactory1;
            instance = new WeatherFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    public String getHttpResponse(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String getLongditude(String input){
        String tmp[] = input.split("longitude");
        //System.out.println(tmp[1].substring(2,8));
        return tmp[1].substring(2,8);
    }
    private String getLatitude(String input){
        String tmp[] = input.split("latitude");
        //System.out.println(tmp[1].substring(2,8));
        return tmp[1].substring(2,8);
    }

    private String getCurrentConditions(String input){
        String tmp[] = input.split("currentConditions");
        return tmp[1];
    }

    private Float getTemp(String input){
        String tmp[] = input.split("temp");
        System.out.println(tmp[1].substring(2,6));
        return Float.parseFloat(tmp[1].substring(2,6));
    }

    private String getConditions(String input){
        String tmp[] = input.split("conditions" + '"' + ":" + '"');
        String tem2[] = tmp[1].split("" + '"' +",");
        System.out.println(tem2[0]);
        return tem2[0];
    }


    public WeatherDTO createWeatherDTO(String input){
        String latitude = getLatitude(input);
        String longditude = getLongditude(input);
        Float temp = getTemp(getCurrentConditions(input));
        String condition = getConditions(getCurrentConditions(input));

        return new WeatherDTO(latitude,longditude,temp,condition);
    }

    public WeatherDTO createWeatherInDB(WeatherDTO weatherDTO){
        Weather weather = new Weather( weatherDTO.getTempInC(), weatherDTO.getCondition());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(weather);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WeatherDTO(weather);
    }

    public CityDTO createCityInDB(CityDTO cityDTO){
        City city = new City(cityDTO.getName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CityDTO(city);
    }
}
