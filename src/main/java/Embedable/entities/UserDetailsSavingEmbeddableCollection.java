package Embedable.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_USER_DETAILS")
public class UserDetailsSavingEmbeddableCollection {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@ElementCollection
	@JoinTable(name = "TBL_USER_DETAILS_ADDRESSES", joinColumns = @JoinColumn(name = "USER_ID"))
	@GenericGenerator(strategy = "uuid", name = "myStrategy")
	@CollectionId(type = @Type(type = "string"), generator = "myStrategy", columns = {@Column(name = "ADDRESS_ID")})
	private Collection<Address> lisOfAddresses = new ArrayList<Address>();

	public UserDetailsSavingEmbeddableCollection(String userName, String phone, Date dob) {
		this.userName = userName;
	}
}
