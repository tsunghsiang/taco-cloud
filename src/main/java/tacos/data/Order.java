package tacos.data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
/*
 * Declare Order as a JPA entity
 * */
@Entity
@Table(name="Taco_Order")
public class Order {
	/**
	 * Designate the property that will uniquely identify the entity in the database
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date placedAt;
	@NotBlank(message="Name is required")
	private String name;
	@NotBlank(message="Street is required")
	private String street;
	@NotBlank(message="City is required")
	private String city;
	@NotBlank(message="State is required")
	private String state;
	@NotBlank(message="Zip code is required")
	private String zip;
	@CreditCardNumber(message="Not a valid credit card number. Format: XXXXXXXXXXXXXXXX")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			 message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	@ManyToOne
	private User user;
	@ManyToMany(targetEntity=Taco.class)
	private List<Taco> tacos = new ArrayList<Taco>();

	
	public Long getId() { return id; }
	public Date getPlacedAt() { return placedAt; }
	public String getName() { return name; }
	public String getStreet() {return street;}
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getZip() { return zip; }
	public String getCcNumber() {return ccNumber;}
	public String getCcExpiration() { return ccExpiration; }
	public String getCcCVV() { return ccCVV; }
	public User getUser() { return user; }
	public List<Taco> getTacos() { return tacos; }
	
	public void setId(Long id) { this.id = id; }
	public void setPlacedAt(Date date) { this.placedAt = date; }
	public void setName(String name) { this.name = name; }
	public void setStreet(String street) { this.street = street; }
	public void setCity(String city) { this.city = city; }
	public void setState(String state) { this.state = state; }
	public void setZip(String zip) { this.zip = zip; }
	public void setCcNumber(String ccNumber) { this.ccNumber = ccNumber;}
	public void setCcExpiration(String ccExpiration) { this.ccExpiration = ccExpiration; }
	public void setCcCVV(String ccCVV) { this.ccCVV = ccCVV; }	
	public void setUser(User user) { this.user = user; }
	public void setTacos(Taco taco) { this.tacos.add(taco); }
	
	public String toString() {
		String result = String.format(	"[Order] Name: %s\n"
										+ "[Order] Street: %s\n"
										+ "[Order] City: %s\n"
										+ "[Order] State: %s\n"
										+ "[Order] ZIP Code: %s\n"
										+ "[Order] Credit Card #: %s\n"
										+ "[Order] Expiration: %s\n"
										+ "[Order] CVV: %s\n"
										+ "[Order] User: %s", name, street, city, state, zip, ccNumber, ccExpiration, ccCVV, user.getUsername());
		return result;
	}
	
	/**
	 * Set property 'placedAt' before Order is persisted
	 */
	@PrePersist
	public void placedAt() { this.placedAt = new Date(); }	
}
