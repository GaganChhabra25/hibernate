package in.gagan.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.gagan.DataHelper.Helper;
import in.gagan.entities.Course3;
import in.gagan.entities.Student3;
import util.HibernateUtil;


public class PersistenceTest {
	private Session session;

	@BeforeMethod
	public void setup() {
		// one unit of work per session
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@Test(description = "Insert java object(data) into database")
	public void saveMessage() {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(new Course3("gagan"));
		session.saveOrUpdate(new Course3("Ankit"));
		transaction.commit();
		session.close();
	}

	@Test(description = "Insert java object(data) into database")
	public void saveCourseWithStudent() {
		Transaction transaction = session.beginTransaction();
		Student3 gagan = new Student3("Gagan", "Chhabra");

		Course3 course = new Course3("Java");
		Course3 course21 = new Course3("Scala");
		Course3 course22 = new Course3("Spring");

		List<Course3> course3List = new ArrayList<Course3>();
		course3List.add(course);
		course3List.add(course21);
		course3List.add(course22);
		System.out.println("888888888888888888888888");

		Helper.linkCourseWithStudent(session, gagan, course3List);
		System.out.println("999999999999999999999999");

		transaction.commit();
		//	session.flush();
		session.close();
	}

	@Test
	public void manyToMany() {
		Transaction transaction = session.beginTransaction();
		Student3 gagan = new Student3("Gagan", "Chhabra");
		Student3 prashant = new Student3("Prashant", "Gupta");

		Course3 course = new Course3("Java");
		Course3 course21 = new Course3("Scala");
		Course3 course22 = new Course3("Spring");

		List<Course3> course3List = new ArrayList<Course3>();
		course3List.add(course);
		course3List.add(course21);
		course3List.add(course22);

		Helper.linkCourseWithStudent(session, gagan, course3List);
		Helper.linkCourseWithStudent(session, prashant, course3List);
		session.flush();
		//transaction.commit();
		session.close();
	}

	@Test(description = "Read java objet(data) from database")
	public void readData() {
		/* ===========Read using query================*/
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Message");
		System.out.println("query = " + query.toString());
		List<Course3> list = query.list();
		list.forEach(course -> System.out.println("Retrieved element = " + list));
		assertThat(list.get(0).getCourseName()).isEqualTo("gagan");
		assertThat(list.get(1).getCourseName()).isEqualTo("Ankit");
		assertThat(list.size()).isEqualTo(2);
		transaction.commit();
		session.close();
	}

	@Test
	public void deleteData() {
		/* =================== Read and delete=========================*/
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Message");
		System.out.println("query = " + query.toString());
		List<Course3> list = query.list();
		//delete object
		session.delete(list.get(0));
		transaction.commit();

		/* ================Load object and delete====================*/
		Transaction transaction1 = session.beginTransaction();
		Object object = session.load(Course3.class, 9L);
		if (object != null) {
			session.delete(object);
		} else {
			System.out.println("No object found");
		}
		transaction1.commit();

		/*===================== Create delete query and execute======================*/
		Transaction transaction2 = session.beginTransaction();
		Query query1 = session.createQuery("delete Message where id = :id");
		query1.setParameter("id", 14L);
		int result = query1.executeUpdate();
		if (result > 0) {
			System.out.println("Message deleted successfully");
		} else {
			System.out.println("No message found with Id = 14");
		}
		transaction2.commit();

		/*============= Delete transient object====================*/
		Transaction transaction4 = session.beginTransaction();
		Course3 message = new Course3();
		message.setId(15L);
		session.delete(message);
		transaction4.commit();

		session.close();
	}
}
