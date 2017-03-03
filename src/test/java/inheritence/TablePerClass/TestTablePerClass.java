package inheritence.TablePerClass;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import inheritence.TablePerClass.entities.FourWheeler;
import inheritence.TablePerClass.entities.TwoWheeler;
import inheritence.TablePerClass.entities.Vehicle;
import util.HibernateUtil;

public class TestTablePerClass {

	@Test
	public void testSingleTable() {

		Session session = new HibernateUtil().initializeHibernateProperties().openSession();
		Transaction transaction = session.beginTransaction();

		Vehicle vehicle = new Vehicle("Truck");
		TwoWheeler pulsar = new TwoWheeler("Pulsar", "Handle");
		FourWheeler i20 = new FourWheeler("i20", "Steering");

		session.save(vehicle);
		session.save(pulsar);
		session.save(i20);

		transaction.commit();
		session.close();

		/* =============================================================
				TABLE PER CLASS
		   ==============================================================


		* mysql> select * from VEHICLE_1;
		+-----------+-------------+
		| vehicleId | vehicleName |
		+-----------+-------------+
		|         1 | Truck       |
		+-----------+-------------+

		mysql> select * from FourWheeler;
		+-----------+-------------+---------------------+
		| vehicleId | vehicleName | steeringFourWheeler |
		+-----------+-------------+---------------------+
		|         3 | i20         | Steering            |
		+-----------+-------------+---------------------+
		1 row in set (0.00 sec)

		mysql> select * from TwoWheeler;
		+-----------+-------------+--------------------+
		| vehicleId | vehicleName | steeringTwoWheeler |
		+-----------+-------------+--------------------+
		|         2 | Pulsar      | Handle             |
		+-----------+-------------+--------------------+

		==================================================================
				TABLE JOINED
		======================================================================





		* */

	}
}
