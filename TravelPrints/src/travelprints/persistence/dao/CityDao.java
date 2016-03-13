package travelprints.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import travelprints.persistence.objects.City;

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
}
