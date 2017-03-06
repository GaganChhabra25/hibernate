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
@DiscriminatorValue("Car")
public class FourWheeler1 extends Vehicle1 {

	private String steeringFourWheeler;

	public FourWheeler1(String steeringFourWheeler) {
		this.steeringFourWheeler = steeringFourWheeler;
	}

	public FourWheeler1(String vehicleName, String steeringFourWheeler) {
		super(vehicleName);
		this.steeringFourWheeler = steeringFourWheeler;
	}

}
