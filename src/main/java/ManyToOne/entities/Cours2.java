package ManyToOne.entities;

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
* 	One  to  Many
*
	(One Course) can have (multiple Students)
* */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "course_m2o")
public class Cours2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String courseName;

	@OneToMany(mappedBy = "cours2")
	private Set<Student2> student2 = new HashSet<Student2>();

	public Cours2(String courseName) {
		this.courseName = courseName;
	}
}
