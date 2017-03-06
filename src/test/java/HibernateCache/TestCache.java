package HibernateCache;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.testng.annotations.Test;

import Cache.entities.Players;
import inheritence.CriteriaTesting.TestCriterias;
import inheritence.TablePerClass.entities.Vehicle;
import util.HibernateUtil;

@Slf4j
public class TestCache {
	TestCriterias testCriterias = new TestCriterias();

	@Test
	public void testSessionLevelcache() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session1.beginTransaction();

		testCriterias.insertData();

		log.info("=========== Firing first select query to database ==============");
		Vehicle vehicle = (Vehicle) session1.get(Vehicle.class, 1l);
		log.info("========== Vehicle object loaded from database===============" + vehicle.getVehicleName());

		log.info("===== Again requesting same vehicle==========");
		Vehicle vehicle2 = (Vehicle) session1.get(Vehicle.class, 1l);
		log.info("====== No quey fired ");
		transaction.commit();

		session1.close();

		/*
		* main] INFO HibernateCache.TestCache - =========== Firing first select query to database ==============
		Hibernate:
			select
				vehicle0_.vehicleId as vehicleI1_22_0_,
				vehicle0_.vehicleName as vehicleN2_22_0_,
				vehicle0_1_.steeringTwoWheeler as steering1_19_0_,
				vehicle0_2_.steeringFourWheeler as steering1_10_0_,
				vehicle0_3_.model as model1_1_0_,
				case
					when vehicle0_3_.vehicleId is not null then 3
					when vehicle0_1_.vehicleId is not null then 1
					when vehicle0_2_.vehicleId is not null then 2
					when vehicle0_.vehicleId is not null then 0
				end as clazz_0_
			from
				VEHICLE_1 vehicle0_
			left outer join
				TwoWheeler vehicle0_1_
					on vehicle0_.vehicleId=vehicle0_1_.vehicleId
			left outer join
				FourWheeler vehicle0_2_
					on vehicle0_.vehicleId=vehicle0_2_.vehicleId
			left outer join
				Audi vehicle0_3_
					on vehicle0_.vehicleId=vehicle0_3_.vehicleId
			where
				vehicle0_.vehicleId=?
		[main] INFO HibernateCache.TestCache - ========== Vehicle object loaded from database===============Truckkkkkk
		[main] INFO HibernateCache.TestCache - ===== Again requesting same vehicle==========
		[main] INFO HibernateCache.TestCache - ====== No quey fired
		*
		*
		* */
	}

	@Test
	public void testSecondLevelCache() {
		log.info("======= Inserting data into Players table==============");
		insertData();
		log.info("========== Data inseert successfully =================");

		log.info("========= Session 1 started ===============");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("s = " + sessionFactory.toString());
		Session session1 = sessionFactory.openSession();
		Transaction transaction = session1.beginTransaction();
		log.info("========= Querying database to fetch record from Players table=========");
		Criteria criteria = session1.createCriteria(Players.class);
		criteria.add(Restrictions.eq("name", "Sachin Tendulkar"));
		List<Players> players = criteria.list();
		players.forEach(players1 ->
				log.info("Player found  = " + players1.getName())
		);
		transaction.commit();
		session1.close();
		log.info("=========  Sesiion 1 closes ====================");

		log.info("========= Session 2 started ===============");
		Session session2 = sessionFactory.openSession();
		Transaction transaction1 = session2.beginTransaction();
		log.info("======= Trying to fetch same player from second session ===============");
		Criteria criteria1 = session2.createCriteria(Players.class);
		criteria.add(Restrictions.eq("name", "Sachin Tendulkar"));
		List<Players> players2 = criteria1.list();
		players2.forEach(players1 ->
				log.info("Player found  from cache = " + players1.getName())
		);
		log.info("=====No query should be fired. Data will be retrieved from sessionFactory ====");
		transaction1.commit();
		session2.close();


		/* ========================= OUTPUT ====================================================================================================================




							[main] INFO HibernateCache.TestCache - ======= Inserting data into Players table==============


							Hibernate:
							insert
							into
								Players
								(email, player_name)
							values
								(?, ?)
						Hibernate:
							insert
							into
								Players
								(email, player_name)
							values
								(?, ?)
						Hibernate:
							insert
							into
								Players
								(email, player_name)
							values
								(?, ?)
						Hibernate:
							insert
							into
								Players
								(email, player_name)
							values
								(?, ?)
						[main] INFO HibernateCache.TestCache - ========== Data inseert successfully =================
						[main] INFO HibernateCache.TestCache - ========= Session 1 started ===============
						main] INFO HibernateCache.TestCache - ========= Querying database to fetch record from Players table=========
						Hibernate:
							select
								this_.player_id as player1_66_0_,
								this_.email as email66_0_,
								this_.player_name as player3_66_0_
							from
								Players this_
							where
								this_.player_name=?
						[main] INFO HibernateCache.TestCache - Player found  = Sachin Tendulkar
						[main] INFO HibernateCache.TestCache - =========  Sesiion 1 closes ====================


		* 					[main] INFO HibernateCache.TestCache - ========= Session 2 started ===============
							[main] INFO HibernateCache.TestCache - ======= Trying to fetch same player from second session ===============
							[main] INFO HibernateCache.TestCache - Player found  from cache = Sachin Tendulkar
							[main] INFO HibernateCache.TestCache - =====No query should be fired. Data will be retrieved from sessionFactory ====
		*
		*
		* */

	}

	private void insertData() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session1.beginTransaction();

		Players sachin = new Players("Sachin Tendulkar", "sachin@gmail.com");
		Players virat = new Players("Virat Kohli", "virat@gmail.com");
		Players ganguly = new Players("Saurav Ganguly", "saurav@gmail.com");
		Players dravid = new Players("Rahul Dravid", "rahul@gmail.com");

		session1.save(sachin);
		session1.save(virat);
		session1.save(ganguly);
		session1.save(dravid);

		transaction.commit();
		session1.close();
	}


}
