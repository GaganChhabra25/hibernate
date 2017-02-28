package OneToMany.TestClass;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import OneToMany.Entities.Course1;
import OneToMany.Entities.Student1;
import OneToMany.datahelper.DataHelper;
import util.HibernateUtil;


@Slf4j
public class TestOneToMany {

	private Session session;

	public void setup() {
		// one unit of work per session
		session = new HibernateUtil().initializeHibernateProperties().openSession();
	}

	@Test(description = "One To Many relationship -> Insert java object(data) into database.")
	public void saveCourseWithStudent() {
		setup();
		Transaction transaction = session.beginTransaction();
		Student1 gagan = new Student1("Gagan", "Chhabra");
		session.saveOrUpdate(gagan);

		List<Course1> course1List = DataHelper.createListOfCourses();
		course1List.forEach(course1 -> {
			gagan.getCourseList().add(course1);
			course1.setStudent1(gagan);
			session.saveOrUpdate(course1);
		});

		transaction.commit();
		session.close();
	}
}