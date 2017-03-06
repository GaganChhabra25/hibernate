package ManyToOne.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ManyToOne.entities.Cours2;
import ManyToOne.entities.Student2;

public class DataHelperForManyToOne {


	public static Set<Student2> createSetOfStudents() {

		Set<Student2> student2Set = new HashSet<Student2>();
		Student2 student = new Student2("Gagan");
		Student2 student11 = new Student2("prashant");
		Student2 student12 = new Student2("Amrit");

		student2Set.add(student);
		student2Set.add(student11);
		student2Set.add(student12);

		return student2Set;
	}


	public static List<Cours2> createListOfCourses() {
		List<Cours2> cours2List = new ArrayList<Cours2>();

		Cours2 cours2 = new Cours2("Java");
		Cours2 cours21 = new Cours2("Java");
		Cours2 cours22 = new Cours2("Java");

		cours2List.add(cours2);


		return cours2List;

	}
}
