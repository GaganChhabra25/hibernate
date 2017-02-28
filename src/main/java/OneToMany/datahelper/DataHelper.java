package OneToMany.datahelper;

import java.util.ArrayList;
import java.util.List;

import OneToMany.Entities.Course1;

public class DataHelper {

	public static List<Course1> createListOfCourses() {
		List<Course1> course1List = new ArrayList<Course1>();

		Course1 course = new Course1("Java");
		Course1 course1 = new Course1("Scala");
		Course1 course2 = new Course1("Spring");
		course1List.add(course);
		course1List.add(course1);
		course1List.add(course2);

		return course1List;

	}
}
