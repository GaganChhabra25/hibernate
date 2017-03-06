package in.gagan.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "students")
public class Student3 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fName;
	private String lName;

	@ManyToMany
	private Set<Course3> course3List = new HashSet<>();

	public Student3(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}
}
