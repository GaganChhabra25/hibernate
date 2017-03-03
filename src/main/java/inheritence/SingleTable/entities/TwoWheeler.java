package inheritence.SingleTable.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Bike")
public class TwoWheeler extends Vehicle {

	private String steeringTwoWheeler;

	public TwoWheeler(String steeringTwoWheeler) {
		this.steeringTwoWheeler = steeringTwoWheeler;
	}

	public TwoWheeler(String vehicleName, String steeringTwoWheeler) {
		super(vehicleName);
		this.steeringTwoWheeler = steeringTwoWheeler;
	}

}
