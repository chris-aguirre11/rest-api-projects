package travelprints.persistence.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import travelprints.persistence.objects.State;

public class StateDao {
	
	public static void addNewState(String stateName, String countryName) {
		Session session = null;
		Transaction t = null;
		try {
			session = new Configuration().configure().buildSessionFactory().openSession();  
			t = session.beginTransaction();  
			t.begin();
			
			State newState = new State();
			newState.setStateName(stateName);
			newState.setCountryName(countryName);
			session.save(newState);
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
