package inheritence.TablePerClass.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
