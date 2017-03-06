package in.gagan.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "courses")
public class Course3 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String courseName;


	@ManyToMany
	@JoinTable(name = "course_student")
	Set<Student3> student3s = new HashSet<>();

	public Course3(String courseName) {
		this.courseName = courseName;
	}

	public Set<Student3> getStudent3s() {
		return student3s;
	}

	public void setStudent3s(Set<Student3> student3s) {
		this.student3s = student3s;
	}

}
