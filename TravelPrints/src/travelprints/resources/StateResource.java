package travelprints.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import travelprints.persistence.dao.CityDao;
import travelprints.persistence.objects.City;

@Path("/state/{state}/cities")
public class StateResource {
	
	@GET
	@Produces("application/json")	
	public List<City> getCitiesInState(@PathParam("state") String stateName) {
        return CityDao.retrieveCitiesInState(stateName);
    }
}
