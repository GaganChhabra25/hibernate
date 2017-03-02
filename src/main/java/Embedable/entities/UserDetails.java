package Embedable.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Embedded //For value type object
	private Address address;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "perm_street")),
			@AttributeOverride(name = "city", column = @Column(name = "perm_city")),
			@AttributeOverride(name = "state", column = @Column(name = "perm_state")),
			@AttributeOverride(name = "pincode", column = @Column(name = "perm_pin"))
	})
	private Address permanentAddress;

	@Column(name = "USER_PHONE")
	private String phone;

	@Column(name = "DOB")
	private Date dob;

	public UserDetails(String userName, String phone, Date dob) {
		this.userName = userName;
		this.phone = phone;
		this.dob = dob;
	}
}