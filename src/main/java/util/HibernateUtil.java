package util;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Cascading.entities.Car;
import Cascading.entities.Person;
import Embedable.entities.UserDetails;
import OneToMany.Entities.Course1;
import OneToMany.Entities.Student1;
import in.gagan.entities.Course;
import in.gagan.entities.Student;
import oneToOne.entities.Address;
import oneToOne.entities.Employee;


/*
	createDatabaseIfNotExist
	--------------------------
 	Creates the database given in the URL if it doesn't yet exist. Assumes the configured user has permissions to create databases.
	Default: false

* */

@Slf4j
public class HibernateUtil {

	private SessionFactory sessionFactory;

	public SessionFactory initializeHibernateProperties() {
		if (sessionFactory != null)
			return sessionFactory;
		try {
			//Hibernate properties
			Properties properties = new Properties();
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Test?autoReconnect=true&amp;createDatabaseIfNotExist=true");
			properties.setProperty("hibernate.connection.username", "root");
			properties.setProperty("hibernate.connection.password", "password");

			properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
			// hibernate logs on/off
			properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.format_sql", "true");

			sessionFactory = new Configuration()
					//.addPackage("in.gagan.entities")
					.addPackage("OneToMany.Entities")
					.addProperties(properties)
					.addAnnotatedClass(Course1.class)
					.addAnnotatedClass(Student1.class)

					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(Student.class)

					.addAnnotatedClass(Employee.class)
					.addAnnotatedClass(Address.class)

					.addAnnotatedClass(ManyToOne.entities.Course.class)
					.addAnnotatedClass(ManyToOne.entities.Student.class)

					.addAnnotatedClass(ManyToMany.entities.Course.class)
					.addAnnotatedClass(ManyToMany.entities.Student.class)

					.addAnnotatedClass(UserDetails.class)
					.addAnnotatedClass(Embedable.entities.Address.class)
					.addAnnotatedClass(Embedable.entities.UserDetailsSavingEmbeddableCollection.class)

					.addAnnotatedClass(Person.class)
					.addAnnotatedClass(Car.class)
					.configure()
					.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
