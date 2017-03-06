package Embedable.entities;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import util.HibernateUtil;

public class TestEmbeddable {

	@Test
	public void testEmbeddableAnnotation() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		UserDetails userDetails1 = new UserDetails();
		UserDetails userDetails2 = new UserDetails();

		userDetails1.setUserName("Gagan");
		userDetails2.setUserName("Ankit");


		Address address1 = new Address(); //create first value type object for  entity type object user1
		address1.setStreet("K Block House No. 403");
		address1.setCity("Mangol Puri");
		address1.setState("New Delhi");
		address1.setPincode("110083");
		userDetails1.setAddress(address1);


		Address address2 = new Address();//create second value type object for  entity type object user2
		address2.setStreet("Film City");
		address2.setCity("Noida");
		address2.setState("UP");
		address2.setPincode("201301");
		userDetails2.setAddress(address2);

		Address address3 = new Address();//create second value type object for  entity type object user2
		address3.setStreet("Rose park");
		address3.setCity("Jalandhar");
		address3.setState("Punjab");
		address3.setPincode("144008");
		userDetails1.setPermanentAddress(address3);

		userDetails1.setDob(new Date());
		userDetails1.setPhone("+91-9953423462");

		userDetails2.setDob(new Date());
		userDetails2.setPhone("+91-9973423462");

		session.save(userDetails1);
		session.save(userDetails2);
		transaction.commit();
		session.close();



		/*
		*			OUTPUT WITH SINGLE EMBEDDABLE OBJECT ADDRESS
		*		----------------------------------------------------
		*
		* 	| USER_ID | CITY_NAME   | PIN_CODE | STATE_NAME | STREET_NAME           | DOB                 | USER_PHONE     | USER_NAME |
			+---------+-------------+----------+------------+-----------------------+---------------------+----------------+-----------+
			|       1 | Mangol Puri | 110083   | New Delhi  | K Block House No. 403 | 2017-03-02 11:08:02 | +91-9953423462 | Gagan     |
			|       2 | Noida       | 201301   | UP         | Film City             | 2017-03-02 11:08:02 | +91-9973423462 | Ankit     |
			+---------+-------------+----------+------------+-----------------------+---------------------+----------------+-----------+


					OUTPUT WITH TWO EMBEDDABLE OBJECTS
					--------------------------------------

					+---------+-------------+----------+------------+-----------------------+---------------------+-----------+----------+------------+-------------+---------
			| USER_ID | CITY_NAME   | PIN_CODE | STATE_NAME | STREET_NAME           | DOB                 | perm_city | perm_pin | perm_state | perm_street | USER_PHONE     | USER_NAME |
			+---------+-------------+----------+------------+-----------------------+---------------------+-----------+----------+------------+-------------+----------------+-----------+
			|       1 | Mangol Puri | 110083   | New Delhi  | K Block House No. 403 | 2017-03-02 11:34:49 | Jalandhar | 144008   | Punjab     | Rose park   | +91-9953423462 | Gagan     |
			|       2 | Noida       | 201301   | UP         | Film City             | 2017-03-02 11:34:49 | NULL      | NULL     | NULL       | NULL        | +91-9973423462 | Ankit     |
			+---------+-------------+----------+------------+-----------------------+---------------------+-----------+----------+------------+-------------+----------------+-----------+

		*
		*
		* */
	}

	@Test(description = "Internally it will create ONE-TO-Many relationship")
	public void testEmbeddableToSaveCollectionIntoDatabase() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		// Create User
		UserDetailsSavingEmbeddableCollection userDetails1 = new UserDetailsSavingEmbeddableCollection();
		userDetails1.setUserName("Gagan");

		// Create multiple addresses of this user
		Address address1 = new Address(); //create first value type object for  entity type object user1
		address1.setStreet("K Block House No. 403");
		address1.setCity("Mangol Puri");
		address1.setState("New Delhi");
		address1.setPincode("110083");


		Address address2 = new Address();//create second value type object for  entity type object user2
		address2.setStreet("Film City");
		address2.setCity("Noida");
		address2.setState("UP");
		address2.setPincode("201301");


		Address address3 = new Address();//create second value type object for  entity type object user2
		address3.setStreet("Rose park");
		address3.setCity("Jalandhar");
		address3.setState("Punjab");
		address3.setPincode("144008");

		// Add addresse to address list
		userDetails1.getLisOfAddresses().add(address1);
		userDetails1.getLisOfAddresses().add(address2);
		userDetails1.getLisOfAddresses().add(address3);

		session.save(userDetails1);
		transaction.commit();
		session.close();

	}
}
