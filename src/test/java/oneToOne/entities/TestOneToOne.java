package oneToOne.entities;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.HibernateUtil;

public class TestOneToOne {


	@BeforeMethod
	public void setup() {
		// one unit of work per session
//		session = new HibernateUtil().initializeHibernateProperties().openSession();
	}

	@Test
	public void testOneToOne() {
		Session session = new HibernateUtil().initializeHibernateProperties().openSession();

		Employee employee = new Employee("Gagan", "gagan@gmail.com");
		Address address = new Address(employee, "Rose park", "Jalandhar", "Punjab", "India", 144008);
		employee.setAddress(address);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(employee);
		session.saveOrUpdate(address);
		transaction.commit();
		session.close();
	}

	@Test
	public void testEagerFetchForEmployeeFromAddressEntity() {
		Session firstInning = new HibernateUtil().initializeHibernateProperties().openSession();

		Employee employee = new Employee("Gagan", "gagan@gmail.com");
		Address address = new Address(employee, "Rose park", "Jalandhar", "Punjab", "India", 144008);
		employee.setAddress(address);
		Transaction transaction = firstInning.beginTransaction();
		Long employeeId = (Long) firstInning.save(employee);
		Long addressId = (Long) firstInning.save(address);
		transaction.commit();
		firstInning.close();

		Session secondInning = new HibernateUtil().initializeHibernateProperties().openSession();
		employee = null;
		address = null;

//		employee = session.get(Employee.class, employeeId);
		address = secondInning.get(Address.class, addressId);
		assert address != null;
		address.getEmployee();
//		assert employee != null;

		transaction = secondInning.beginTransaction();
		secondInning.delete(address);
//		session.delete(employee);

		secondInning.close();
	}

	@Test
	public void testEagerFetchForAddressFromEmployeeEntity() {
		Session firstInning = new HibernateUtil().initializeHibernateProperties().openSession();

		Employee employee = new Employee("Gagan", "gagan@gmail.com");
		Address address = new Address(employee, "Rose park", "Jalandhar", "Punjab", "India", 144008);
		employee.setAddress(address);
		Transaction transaction = firstInning.beginTransaction();
		Long employeeId = (Long) firstInning.save(employee);
		Long addressId = (Long) firstInning.save(address);
		transaction.commit();
		firstInning.close();

		Session secondInning = new HibernateUtil().initializeHibernateProperties().openSession();
		employee = null;

		employee = secondInning.get(Employee.class, employeeId);
		employee.getAddress();
		assert employee != null;

		transaction = secondInning.beginTransaction();
		secondInning.delete(employee);
		transaction.commit();

		secondInning.close();
	}
}
