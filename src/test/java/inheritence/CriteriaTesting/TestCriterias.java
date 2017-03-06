package inheritence.CriteriaTesting;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.testng.annotations.Test;

import inheritence.TablePerClass.entities.FourWheeler;
import inheritence.TablePerClass.entities.TwoWheeler;
import inheritence.TablePerClass.entities.Vehicle;
import util.HibernateUtil;

@Slf4j
public class TestCriterias {

	@Test
	public void testJoinedInheritenceusingCriteria() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		// Create Data
		insertData();

		// It will get all rows present in Vehicle table;
		Criteria criteria = session.createCriteria(Vehicle.class);

		// It will restrict the result where vehicleName = Pulsar
		criteria.add(Restrictions.eq("vehicleName", "Pulsar"));

		List<Vehicle> vehicleList = criteria.list();
		vehicleList.forEach(vehicle -> {
			log.info(vehicle.getVehicleName());
		});

		transaction.commit();
		session.close();

	}

	public void insertData() {

		Vehicle vehicle = new Vehicle("Truckkkkkk");

		TwoWheeler pulsar = new TwoWheeler("Pulsar", "Solid handle");
		TwoWheeler cbz = new TwoWheeler("CBZ", "Straight handle");
		TwoWheeler passion = new TwoWheeler("Passion", "Handle");

		FourWheeler hCity = new FourWheeler("Honda City", "Circular");
		FourWheeler santro = new FourWheeler("Santro", "Oval");

		List<TwoWheeler> twoWheelerList = new ArrayList<TwoWheeler>();
		List<FourWheeler> fourWheelerList = new ArrayList<FourWheeler>();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();

		vehicleList.add(vehicle);

		twoWheelerList.add(pulsar);
		twoWheelerList.add(cbz);
		twoWheelerList.add(passion);

		fourWheelerList.add(hCity);
		fourWheelerList.add(santro);

		saveEntities(vehicleList);
		saveEntities(twoWheelerList);
		saveEntities(fourWheelerList);
	}

	public <T> void saveEntities(List<T> t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		t.forEach(t1 -> {
			session.save(t1);
		});
		transaction.commit();
		session.close();
	}
}
