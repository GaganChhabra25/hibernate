package inheritence.TablePerClass;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import inheritence.TablePerClass.entities.Audi;
import inheritence.TablePerClass.entities.FourWheeler;
import inheritence.TablePerClass.entities.TwoWheeler;
import inheritence.TablePerClass.entities.Vehicle;
import util.HibernateUtil;

public class TestTablePerClass {

	@Test
	public void testTablePerClass() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Vehicle vehicle = new Vehicle("Truck");
		Vehicle vehicle1 = new Vehicle("Bus");
		TwoWheeler pulsar = new TwoWheeler("Pulsar", "Handle");
		FourWheeler i20 = new FourWheeler("i20", "Steering");
		Audi a3 = new Audi("Audi", "personalSteering", "12ga2");
		session.save(vehicle);
		session.save(vehicle1);
		session.save(pulsar);
		session.save(i20);
		session.save(a3);

		transaction.commit();
		session.close();

	}
}
