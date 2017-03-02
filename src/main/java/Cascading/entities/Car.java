package Cascading.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String carName;

	@ManyToOne
	private Person person;

	public Car(String carName) {
		this.carName = carName;
	}
}
