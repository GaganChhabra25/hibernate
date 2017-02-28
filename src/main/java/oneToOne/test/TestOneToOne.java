package oneToOne.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import oneToOne.entities.Address;
import oneToOne.entities.Employee;
import util.HibernateUtil;

public class TestOneToOne {

	private Session session;

	@BeforeMethod
	public void setup() {
		// one unit of work per session
		session = new HibernateUtil().initializeHibernateProperties().openSession();
	}

	@Test
	public void testOneToOne() {
		Employee employee = new Employee("Gagan", "gagan@gmail.com");
		Address address = new Address(employee, "Rose park", "Jalandhar", "Punjab", "India", 144008);
		employee.setAddress(address);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(employee);
		session.saveOrUpdate(address);
		transaction.commit();
		session.close();
	}
}
