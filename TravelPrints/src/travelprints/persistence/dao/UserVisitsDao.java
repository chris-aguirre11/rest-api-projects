package travelprints.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


import travelprints.persistence.objects.City;
import travelprints.persistence.objects.User;
import travelprints.persistence.objects.UserVisits;

public class UserVisitsDao {
	
	static String firstName = null;
	static String middleInitial = null;
	static String lastName = null;
	
	public static void addNewUserVisitsEntry(
			String firstName, 
			String middleInitial, 
			String lastName,
			String cityName, 
			String stateName, 
			String countryName,
			Date dateVisited, 
			int numberOfDays ) {
		
		Session session = null;
		Transaction t = null;
		
			session = new Configuration().configure().buildSessionFactory().openSession();  
			t = session.beginTransaction();  
			t.begin();
			
			// Load all other tables first so foreign keys are valid
			CountryDao.addNewCountry(countryName);
			StateDao.addNewState(stateName, countryName);
			CityDao.addNewCity(cityName, stateName, countryName);
			UserDao.addNewUser(firstName, middleInitial, lastName);
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("firstName", firstName));
			criteria.add(Restrictions.eq("middleInitial", middleInitial));
			criteria.add(Restrictions.eq("lastName", lastName));
			
			UserVisits newUserVisitsEntry = new UserVisits();
			newUserVisitsEntry.setFirstName(firstName);
			newUserVisitsEntry.setMiddleInitial(middleInitial);
			newUserVisitsEntry.setLastName(lastName);
			newUserVisitsEntry.setCityName(cityName);
			newUserVisitsEntry.setStateName(stateName);
			newUserVisitsEntry.setCountryName(countryName);
			newUserVisitsEntry.setDateVisited(dateVisited);
			newUserVisitsEntry.setNumberOfDays(numberOfDays);
			
			session.saveOrUpdate(newUserVisitsEntry);
			
			t.commit();  
			session.close(); 
	} 
	
	public static void deleteUserVisitsEntry(String userName, String cityName) {
		parseUserNameInputString(userName);
		
		Session session = null;
		Transaction t = null;
		session = new Configuration().configure().buildSessionFactory().openSession();  
		t = session.beginTransaction();  
		t.begin();
		
		Criteria criteria = session.createCriteria(UserVisits.class);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("middleInitial", middleInitial));
		criteria.add(Restrictions.eq("lastName", lastName));
		criteria.add(Restrictions.eq("cityName", cityName));
		
		UserVisits deleteUserVisitsEntry = (UserVisits) criteria.uniqueResult();
		if (deleteUserVisitsEntry != null)
			session.delete(deleteUserVisitsEntry);  
		
		t.commit();  
		session.close(); 
	} 
	
	public static List<String> retrieveCitiesUserHasVisited(String userName) { 
		parseUserNameInputString(userName);
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
		Transaction t = session.beginTransaction();  
		t.begin();  
		
		Criteria criteria = session.createCriteria(UserVisits.class);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("middleInitial", middleInitial));
		criteria.add(Restrictions.eq("lastName", lastName));
		criteria.setProjection(Projections.property("cityName"));
		List<String> userVisitedCities = criteria.list();
		
		t.commit();  
		session.close();  
		
		return userVisitedCities;
	}
	
	public static List<String> retrieveStatesUserHasVisited(String userName) { 
		parseUserNameInputString(userName);
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
		Transaction t = session.beginTransaction();  
		t.begin();  
		
		Criteria criteria = session.createCriteria(UserVisits.class);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("middleInitial", middleInitial));
		criteria.add(Restrictions.eq("lastName", lastName));
		criteria.setProjection(Projections.property("stateName"));
		List<String> userVisitedCities = criteria.list();
		
		t.commit();  
		session.close();  
		
		return userVisitedCities;
	}
	
	/**
	 * For example, to handle input strings such as Chris%D%Aguirre from a browser
	 */
	private static void parseUserNameInputString(String userName) {
		String[] splitUserNameString = userName.split(" ");
		if(splitUserNameString.length == 2) {
			firstName = splitUserNameString[0];
			middleInitial = " ";
			lastName = splitUserNameString[1];
		}
		if(splitUserNameString.length == 3) {
			firstName = splitUserNameString[0];
			middleInitial = splitUserNameString[1];
			lastName = splitUserNameString[2];
		}
	}
}
