package travelprints.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import travelprints.api.utils.ApiUtils;
import travelprints.persistence.dao.UserVisitsDao;
import travelprints.persistence.objects.UserVisits;

@Path("/user")
public class UserResource {
	
	@POST
	@Path("/{user}/visits")
	@Consumes("application/json")
	public Response createUserVisitsEntry(@PathParam("user") String user, UserVisits userVisit) {

		UserVisitsDao.addNewUserVisitsEntry(
				userVisit.getFirstName(), 
				userVisit.getMiddleInitial(), 
				userVisit.getLastName(), 
				userVisit.getCityName(), 
				userVisit.getStateName(), 
				userVisit.getCountryName(), 
				userVisit.getDateVisited(), 
				userVisit.getNumberOfDays());
		
		return Response.status(201).entity("Success").build();
		
	}
	
	@DELETE
	@Path("/{user}/visit/{visit}")
	public Response deleteUserVisitsEntry(@PathParam("user") String user, @PathParam("visit") String cityName) {
		UserVisitsDao.deleteUserVisitsEntry(user, cityName);
		return Response.status(201).entity("Success").build();
	}
	
	@GET
	@Path("/{user}/visits")
	@Produces("application/json")	
	public List<String> getCitiesUserHasVisited(@PathParam("user") String user) {
        return UserVisitsDao.retrieveCitiesUserHasVisited(user);
    }
	
	/**
	 * Intended Goal: To provide an XML file format so users of this API can use to call 
	 *    the Google Maps API and easily plot all points latitude/longitude.
	 * 	
	 * Reference: https://developers.google.com/maps/articles/phpsqlajax_v3#checking-that-xml-output-works
	 */
	@GET
	@Path("/{user}/visits/markers")
	@Produces("application/text")	
	public String generateGoogleMapMarkersXML(@PathParam("user") String user) {
        return ApiUtils.generateGoogleMapMarkersXML(user);
    }
	
	@GET
	@Path("/{user}/visits/states")
	@Produces("application/json")	
	public List<String> getStatesUserHasVisited(@PathParam("user") String user) {
        return UserVisitsDao.retrieveStatesUserHasVisited(user);
    }

}
