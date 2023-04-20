package rest;

import com.google.gson.Gson;
import dtos.WeatherDTO;
import facades.WeatherFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weather")
public class WeatherResource {

    private EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private WeatherFacade FACADE = WeatherFacade.getWeatherFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{city}")
    public Response getDadJoke(@PathParam("city")String city) throws Exception {
        System.out.println("getting weather");
        String response = FACADE.getHttpResponse("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+ city + "?unitGroup=us&key=T575QYQBWAZ686YMYP4VGEJDS&contentType=json");
        WeatherDTO weatherDTO = FACADE.createWeatherDTO(response);
        return Response.ok().entity(weatherDTO).build();
    }
}
