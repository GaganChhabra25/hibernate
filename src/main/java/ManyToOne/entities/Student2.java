package ManyToOne.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student_m2o")
public class Student2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String studentName;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Cours2 cours2;

	public Student2(String name) {
		this.studentName = name;
	}
}
