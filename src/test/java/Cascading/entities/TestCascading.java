package Cascading.entities;


import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import util.HibernateUtil;

@Slf4j
public class TestCascading {

	@Test(description = "CascadeType.ALL")
	public void testCascading() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Person person = new Person("Gagan");
		Car santro = new Car("Santro");
		Car hCity = new Car("Honda City");
		Car i20 = new Car("i20");


		person.getCarList().add(santro);
		person.getCarList().add(hCity);
		person.getCarList().add(i20);

		santro.setPerson(person);
		hCity.setPerson(person);
		i20.setPerson(person);

		// As cascading is @Cascade(CascadeType.ALL), save only owning entity, it will save child entity as well
		session.save(person);
		transaction.commit();
		session.close();
	}

	@Test(description = "CascadeType.DELETE")
	public void testCascadingDelete() {
		// save data
		testCascading();
		log.info("=======Data created successfuly========== ");

		// Read and delete
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Person person = (Person) session.get(Person.class, 1l);
		log.info("======= Data retrieved ==============");
		session.delete(person);
		transaction.commit();
		session.close();

		/*
		 Delete operation will first perform on child class i.e CAR class and then parent i.e Person class
		* */
	}
}
