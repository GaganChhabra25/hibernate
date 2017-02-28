package OneToMany.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
	OneToMany
	==============

	1. Annotation level :
		-> 	One Student can have multiple courses
				Student class  : Set<Course> courses;

	2. DB level :
		-> New column will be added into course table(that contains student id)

	3. mappedBy annotation
	   ---------------------
	   How student and course class will be joined. Decided by this annotation

 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students1")
public class Student1 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fName;
	private String lName;

	@OneToMany(mappedBy = "student1")
	private Set<Course1> courseList = new HashSet<>();

	public Student1(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}
}
