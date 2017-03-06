package Cascading.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String name;

	/*	@OneToMany(mappedBy = "person", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.EAGER)*/
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Car> carList = new ArrayList<Car>();

	public Person(String name) {
		this.name = name;
	}
}
