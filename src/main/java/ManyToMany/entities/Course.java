package ManyToMany.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* 	One  to  Many
*
	(One Course) can have (multiple Students)
* */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "course_m2m")
public class Course {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id")
	private Long Id;
	private String courseName;

	@ManyToMany
	@JoinColumn(name = "student_id")
	Set<Student> studentSet = new HashSet<Student>();

	public Course(String courseName) {
		this.courseName = courseName;
	}
}
