package travelprints.persistence.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import travelprints.persistence.objects.User;

public class UserDao {
	
	public static void main(String[] args) {
		UserDao.addNewUser("Chris", " ", "Aguirre");
	}
	
	public static void addNewUser(String firstName, String middleInitial, String lastName) {
		Session session = null;
		Transaction t = null;
		try {
			session = new Configuration().configure().buildSessionFactory().openSession();  
			t = session.beginTransaction();  
			t.begin();
			
			User newUser = new User();
			newUser.setFirstName(firstName);
			newUser.setMiddleInitial(middleInitial);
			newUser.setLastName(lastName);
			session.saveOrUpdate(newUser);
		} 
		catch (ConstraintViolationException e) {
			// TODO: What to do in this case - how to notify user?
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			t.commit();  
			session.close(); 
		}
	} 

}
