package ManyToOne.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ManyToOne.entities.Course;
import ManyToOne.entities.Student;

public class DataHelperForManyToOne {


	public static Set<Student> createSetOfStudents() {

		Set<Student> studentSet = new HashSet<Student>();
		Student student = new Student("Gagan");
		Student student1 = new Student("prashant");
		Student student2 = new Student("Amrit");

		studentSet.add(student);
		studentSet.add(student1);
		studentSet.add(student2);

		return studentSet;
	}


	public static List<Course> createListOfCourses() {
		List<Course> courseList = new ArrayList<Course>();

		Course course = new Course("Java");
		Course course1 = new Course("Java");
		Course course2 = new Course("Java");

		courseList.add(course);


		return courseList;

	}
}
