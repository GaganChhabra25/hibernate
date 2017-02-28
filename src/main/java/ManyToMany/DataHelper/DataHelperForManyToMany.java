package ManyToMany.DataHelper;


import java.util.HashSet;
import java.util.Set;

import ManyToMany.entities.Course;
import ManyToMany.entities.Student;

public class DataHelperForManyToMany {

	public static Set<Student> createListOfStudents() {
		Set<Student> studentSet = new HashSet<Student>();
		Student student = new Student("Gagan");
		Student student1 = new Student("Prashant");
		Student student2 = new Student("Amrit");
		studentSet.add(student);
		studentSet.add(student1);
		studentSet.add(student2);

		return studentSet;

	}

	public static Set<Course> createListOfCourses() {
		Set<Course> courseSet = new HashSet<Course>();
		Course course = new Course("java");
		Course course1 = new Course("Spring");
		Course course2 = new Course("Hibernate");

		courseSet.add(course);
		courseSet.add(course1);
		courseSet.add(course2);

		return courseSet;
	}
}
