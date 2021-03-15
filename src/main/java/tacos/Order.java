package tacos;

public class Order {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	
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
