package Embedable.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
//for value object it is not is entity object. Value object means does not have real meaning for self individually.
@Getter
@Setter
@NoArgsConstructor
public class Address {
	@Column(name = "STREET_NAME")
	private String street;
	@Column(name = "CITY_NAME")
	private String city;
	@Column(name = "STATE_NAME")
	private String state;
	@Column(name = "PIN_CODE")
	private String pincode;

	public Address(String street, String city, String state, String pincode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	// Let parent class handle the ID geneartion for this class.
}
