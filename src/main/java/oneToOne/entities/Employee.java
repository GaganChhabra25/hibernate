package oneToOne.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
	NOTE:
	======
	Column will be added in the class where "mappedBy" annotation is not present.
	In this case -> mappedBy annotation is in address class, so column will be added in Employee class.
	Reverse this and see the difference

 */

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String name, email;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
