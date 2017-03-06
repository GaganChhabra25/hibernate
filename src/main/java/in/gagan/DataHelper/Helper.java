package in.gagan.DataHelper;

import java.util.List;

import org.hibernate.Session;

import in.gagan.entities.Course3;
import in.gagan.entities.Student3;

public class Helper {

	public static void linkCourseWithStudent(Session session, Student3 student3, List<Course3> course3s) {
		try {
			course3s.forEach(course1 ->
			{
				session.saveOrUpdate(course1);
				course1.getStudent3s().add(student3);
			});
			student3.getCourse3List().addAll(course3s);
			session.saveOrUpdate(student3);
		} catch (Exception exception) {
			System.out.println("Unable to Save SingleTable into database");
		}
	}
}
