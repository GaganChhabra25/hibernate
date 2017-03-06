package ManyToOne.test;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ManyToOne.entities.Cours2;
import ManyToOne.entities.Student2;
import ManyToOne.helper.DataHelperForManyToOne;
import util.HibernateUtil;

@Slf4j
public class TestManyToOne {

	private Session session;

	@BeforeMethod
	public void setup() {
		// one unit of work per session
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@Test
	public void testManyToOne() {
		Transaction transaction = session.beginTransaction();

		Set<Student2> student2Set = DataHelperForManyToOne.createSetOfStudents();
		Cours2 cours2 = new Cours2("java");
		/*log.info("==========Course ==============");
		log.info(course.getCourseName());
		System.out.println(course.getId());*/

		student2Set.forEach(student1 -> {
			cours2.getStudent2().add(student1);
			student1.setCours2(cours2);
			session.saveOrUpdate(student1);
		});
		session.saveOrUpdate(cours2);
		transaction.commit();
		session.close();
	}
}
