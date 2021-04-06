package tacos;


import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
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
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			 message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	public Long getId() { return id; }
	public Date getCreatedAt() { return placedAt; }
	public String getName() { return name; }
	public String getStreet() {return street;}
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getZip() { return zip; }
	public String getCcNumber() {return ccNumber;}
	public String getCcExpiration() { return ccExpiration; }
	public String getCcCVV() { return ccCVV; }
	
	public String toString() {
		String result = String.format(	"[Order] Name: %s\n"
										+ "[Order] Street: %s\n"
										+ "[Order] City: %s\n"
										+ "[Order] State: %s\n"
										+ "[Order] ZIP Code: %s\n"
										+ "[Order] Credit Card #: %s\n"
										+ "[Order] Expiration: %s\n"
										+ "[Order] CVV: %s\n", name, street, city, state, zip, ccNumber, ccExpiration, ccCVV);
		return result;
	}
}
