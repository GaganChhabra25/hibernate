package ManyToMany.test;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ManyToMany.DataHelper.DataHelperForManyToMany;
import ManyToMany.entities.Course;
import ManyToMany.entities.Student;
import util.HibernateUtil;

public class TestMany2Many {


	private Session session;

	@BeforeMethod
	public void setup() {
		// one unit of work per session
		session = new HibernateUtil().initializeHibernateProperties().openSession();
	}

	@Test
	public void testMany2Many() {
		Transaction transaction = session.beginTransaction();
		Set<Student> studentSet = DataHelperForManyToMany.createListOfStudents();
		Set<Course> courseSet = DataHelperForManyToMany.createListOfCourses();

		studentSet.forEach(student -> {
			student.setCourseSet(courseSet);
			session.saveOrUpdate(student);
		});

		courseSet.forEach(course -> {
			course.setStudentSet(studentSet);
			session.saveOrUpdate(course);
		});

		transaction.commit();
		session.close();
	}
}
