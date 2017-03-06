package inheritence.TablePerClass.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by gagan on 3/3/17.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Audi extends FourWheeler {

	private String model;

	public Audi(String steeringFourWheeler) {
		super(steeringFourWheeler);
	}

	public Audi(String vehicleName, String steeringFourWheeler, String model) {
		super(vehicleName, steeringFourWheeler);
		this.model = model;
	}
}
