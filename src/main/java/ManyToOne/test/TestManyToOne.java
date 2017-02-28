package ManyToOne.test;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ManyToOne.entities.Course;
import ManyToOne.entities.Student;
import ManyToOne.helper.DataHelperForManyToOne;
import util.HibernateUtil;

@Slf4j
public class TestManyToOne {

	private Session session;

	@BeforeMethod
	public void setup() {
		// one unit of work per session
		session = new HibernateUtil().initializeHibernateProperties().openSession();
	}

	@Test
	public void testManyToOne() {
		Transaction transaction = session.beginTransaction();

		Set<Student> studentSet = DataHelperForManyToOne.createSetOfStudents();
		Course course = new Course("java");
		/*log.info("==========Course ==============");
		log.info(course.getCourseName());
		System.out.println(course.getId());*/

		studentSet.forEach(student1 -> {
			course.getStudent().add(student1);
			student1.setCourse(course);
			session.saveOrUpdate(student1);
		});
		session.saveOrUpdate(course);
		transaction.commit();
		session.close();
	}
}
