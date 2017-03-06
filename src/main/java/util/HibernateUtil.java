package util;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Cache.entities.Players;
import Cascading.entities.Car;
import Cascading.entities.Person;
import Embedable.entities.UserDetails;
import ManyToOne.entities.Cours2;
import ManyToOne.entities.Student2;
import OneToMany.Entities.Course1;
import OneToMany.Entities.Student1;
import in.gagan.entities.Course3;
import in.gagan.entities.Student3;
import inheritence.SingleTable.entities.FourWheeler1;
import inheritence.SingleTable.entities.TwoWheeler1;
import inheritence.SingleTable.entities.Vehicle1;
import inheritence.TablePerClass.entities.Audi;
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
	private static HibernateUtil hibernateUtil;

	private HibernateUtil() {
		sessionFactory = this.initializeHibernateProperties();
	}

	public static SessionFactory getSessionFactory() {
		if (hibernateUtil == null) {
			hibernateUtil = new HibernateUtil();
		}
		return hibernateUtil.sessionFactory;
	}

	private SessionFactory initializeHibernateProperties() {
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

		// Enable second level cache
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "true");
		properties.setProperty("hibernate.cache.region.factory_class", "net.sf.ehcache.hibernate.SingletonEhCacheRegionFactory");

		sessionFactory = new Configuration()
				//.addPackage("in.gagan.SingleTable")
				.addPackage("OneToMany.Entities")
				.addProperties(properties)
				.addAnnotatedClass(Course1.class)
				.addAnnotatedClass(Student1.class)

				.addAnnotatedClass(Course3.class)
				.addAnnotatedClass(Student3.class)

				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Address.class)

				.addAnnotatedClass(Cours2.class)
				.addAnnotatedClass(Student2.class)

				.addAnnotatedClass(ManyToMany.entities.Course.class)
				.addAnnotatedClass(ManyToMany.entities.Student.class)

				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(Embedable.entities.Address.class)
				.addAnnotatedClass(Embedable.entities.UserDetailsSavingEmbeddableCollection.class)

				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Car.class)

				.addAnnotatedClass(Vehicle1.class)
				.addAnnotatedClass(TwoWheeler1.class)
				.addAnnotatedClass(FourWheeler1.class)

				.addAnnotatedClass(inheritence.TablePerClass.entities.Vehicle.class)
				.addAnnotatedClass(inheritence.TablePerClass.entities.TwoWheeler.class)
				.addAnnotatedClass(inheritence.TablePerClass.entities.FourWheeler.class)

				.addAnnotatedClass(Audi.class)

				.addAnnotatedClass(Players.class)

				.configure()
				.buildSessionFactory();
		return sessionFactory;
	}
}
