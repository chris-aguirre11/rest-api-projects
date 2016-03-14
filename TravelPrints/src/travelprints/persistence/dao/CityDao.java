package travelprints.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import travelprints.persistence.objects.City;
import travelprints.persistence.objects.Country;

public class CityDao {
	
	public static List<City> retrieveCitiesInState(String stateName) {  
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
        
		Transaction t = session.beginTransaction();  
		t.begin();  
		
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("stateName", stateName));
		List<City> citiesList = criteria.list();
		
		t.commit();  
		session.close();  
		
		return citiesList;
	}
	
	public static void addNewCity(String cityName, String stateName, String countryName) {
		Session session = null;
		Transaction t = null;
		try {
			session = new Configuration().configure().buildSessionFactory().openSession();  
			t = session.beginTransaction();  
			t.begin();
			
			City newCity = new City();
			newCity.setCityName(cityName);
			newCity.setStateName(stateName);
			newCity.setCountryName(countryName);
			
			// TODO Dynamically get the latitude and longitude from this Google API
			// https://developers.google.com/maps/documentation/geocoding/intro#GeocodingResponses
			Float randomLatitudeValue = (float) (Math.random() * 100) + 1;
			Float randomLongitudeValue = 0 - (float) (Math.random() * 100) + 1;
			newCity.setLatitude(randomLatitudeValue.toString());
			newCity.setLongitude(randomLongitudeValue.toString());
			
			session.saveOrUpdate(newCity);
		} 
		catch (ConstraintViolationException e) {
			// TODO: What to do in this case - how to notify user?
		}
		finally {
			t.commit();  
			session.close(); 
		}
	} 
}
