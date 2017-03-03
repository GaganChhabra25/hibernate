package inheritence.SingleTable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import inheritence.SingleTable.entities.FourWheeler;
import inheritence.SingleTable.entities.TwoWheeler;
import inheritence.SingleTable.entities.Vehicle;
import util.HibernateUtil;

public class TestInheritence {

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

		/*
		 	| DTYPE       | vehicleId | vehicleName | steeringTwoWheeler | steeringFourWheeler |
			+-------------+-----------+-------------+--------------------+---------------------+
			| Vehicle     |         1 | Truck       | NULL               | NULL                |
			| TwoWheeler  |         2 | Pulsar      | Handle             | NULL                |
			| FourWheeler |         3 | i20         | NULL               | Steering            |
			+-------------+-----------+-------------+--------------------+---------------------+

		DRAWBACK:
		---------

		1. If we put any contraint on any column (like steeringTwoWheeler can't be null), then while saving hibernate will throw
		exception

		NOTE:
		---------
		The first column has the value of discriminator type(DTYPE). For user convenience we can override the default value of
		column as well as column name by using the following annotation.

		1. We can change name of descriminator column :

				@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.STRING)
				public class Vehicle {}

		2. We can change values of descriminator column.

				@DiscriminatorValue("Bike")
				public class TwoWheeler extends Vehicle {}


	================= AFTER using descriminator annoattaion ============================

	+--------------+-----------+-------------+--------------------+---------------------+
	| VEHICLE_TYPE | vehicleId | vehicleName | steeringTwoWheeler | steeringFourWheeler |
	+--------------+-----------+-------------+--------------------+---------------------+
	| Vehicle      |         1 | Truck       | NULL               | NULL                |
	| Bike         |         2 | Pulsar      | Handle             | NULL                |
	| Car          |         3 | i20         | NULL               | Steering            |
	+--------------+-----------+-------------+--------------------+---------------------+


		* */
	}
}
