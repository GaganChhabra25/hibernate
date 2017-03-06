package OneToMany.TestClass;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import OneToMany.Entities.Course1;
import OneToMany.Entities.Student1;
import OneToMany.datahelper.DataHelper;
import util.HibernateUtil;


/*
	FETCHING STRATEGIES :
	===========================

	1.  @ManyToOne and @OneToOne annotations are fetched EAGERly
 		@OneToMany and @ManyToMany relationships are considered LAZY.

 	2. By default : all collection are considered LAZY.
 	3. When we do any .get() opertaion on any collection the new hibernate proxy object will be created. This proxy object get the data and provide it.
 	4. LazyInitialization exception will occur when we trying to get something from collection  that is not bound to any session.
 	5. The fetch type essentially decides whether or not to load all of the relationships of a particular object/table as soon as the object/table is initially fetched.

* */
@Slf4j
public class TestOneToMany {

	private Session session;

	public void setup() {
		// one unit of work per session
		session = HibernateUtil.getSessionFactory().openSession();
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

	@Test(description = "hh")
	public void testFetchingStartegies() {
		setup();
		Transaction transaction = session.beginTransaction();

		// Inserting data
		Student1 gagan = new Student1("Gagan", "Chhabra");
		session.saveOrUpdate(gagan);

		List<Course1> course1List = DataHelper.createListOfCourses();
		course1List.forEach(course1 -> {
			gagan.getCourseList().add(course1);
			course1.setStudent1(gagan);
			session.saveOrUpdate(course1);
		});


		Query query = session.createQuery("from Student1");
		List<Student1> student1List = query.list();
		log.info("============ Iterating over list of Persons ==============");
		student1List.forEach(student1 -> {
			log.info("For student  : " + student1.getFName());
			log.info("== Iterating over courses ==");

			// As course is lazy initialize by default, so Proxy obeject of course will be created will be created and this object will get all courses and return it to
			// course1Set.
		/*	Set<Course1> course1Set = student1.getCourseList();
			course1Set.forEach(course1 -> {
				log.info("Course  = " + course1.getCourseName());
				log.info("Course  = " + course1.getCourseName());
			});
			log.info("== Course iteration finished ==");
			log.info("== ============ Iteration finished ===============");*/
		});
		transaction.commit();
		session.close();



		/*   ============== OUTPUT =========================

		[main] INFO OneToMany.TestClass.TestOneToMany - ============ Iterating over list of Persons ==============
		[main] INFO OneToMany.TestClass.TestOneToMany - For student  : Gagan
		[main] INFO OneToMany.TestClass.TestOneToMany - == Iterating over courses ==
		[main] INFO OneToMany.TestClass.TestOneToMany - Course  = Spring
		[main] INFO OneToMany.TestClass.TestOneToMany - Course  = Java
		[main] INFO OneToMany.TestClass.TestOneToMany - Course  = Scala
		[main] INFO OneToMany.TestClass.TestOneToMany - == Course iteration finished ==
		[main] INFO OneToMany.TestClass.TestOneToMany - == ============ Iteration finished ===============

		CONCLUSION :
		============

		1. 	@OneToMany(mappedBy = "student1", fetch = FetchType.EAGER)
			private Set<Course1> courseList = new HashSet<>();



	*/
	}


}