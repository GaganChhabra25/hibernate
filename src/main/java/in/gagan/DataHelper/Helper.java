package in.gagan.DataHelper;

import java.util.List;

import org.hibernate.Session;

import in.gagan.entities.Course;
import in.gagan.entities.Student;

public class Helper {

	public static void linkCourseWithStudent(Session session, Student student, List<Course> courses) {
		try {
			courses.forEach(course1 ->
			{
				session.saveOrUpdate(course1);
				course1.getStudents().add(student);
			});
			student.getCourseList().addAll(courses);
			session.saveOrUpdate(student);
		} catch (Exception exception) {
			System.out.println("Unable to Save SingleTable into database");
		}
	}
}
