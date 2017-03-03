package inheritence.TablePerClass.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FourWheeler extends Vehicle {

	private String steeringFourWheeler;

	public FourWheeler(String steeringFourWheeler) {
		this.steeringFourWheeler = steeringFourWheeler;
	}

	public FourWheeler(String vehicleName, String steeringFourWheeler) {
		super(vehicleName);
		this.steeringFourWheeler = steeringFourWheeler;
	}

}
