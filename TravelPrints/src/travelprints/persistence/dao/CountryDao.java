package travelprints.persistence.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import travelprints.persistence.objects.Country;

public class CountryDao {
	
	public static void addNewCountry(String countryName) {
		Session session = null;
		Transaction t = null;
		try {
			session = new Configuration().configure().buildSessionFactory().openSession();  
			t = session.beginTransaction();  
			t.begin();
			
			Country newCountry = new Country();
			newCountry.setCountryName(countryName);
			session.save(newCountry);
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
