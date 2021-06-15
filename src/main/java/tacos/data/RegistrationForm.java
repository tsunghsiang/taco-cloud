package tacos.data;

import lombok.Data;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String setUsername(String username) {
		return this.username = username;
	}

	public String setPassword(String password) {
		return this.password = password;
	}	
	
	public String setFullname(String fullname) {
		return this.fullname = fullname;
	}
	
	public String setStreet(String street) {
		return this.street = street;
	}
	
	public String setCity(String city) {
		return this.city = city;
	}
	
	public String setState(String state) {
		return this.state = state;
	}
	
	public String setZip(String zip) {
		return this.zip = zip;
	}
	
	public String setPhone(String phone) {
		return this.phone = phone;
	}	
	
	public User toUser() {
		return new User(username, "{noop}" + password, fullname, street, 
						city, state, zip, phone);
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("username: ").append(username).append('\n');
		buf.append("password: ").append(password).append('\n');
		buf.append("fullname: ").append(fullname).append('\n');
		buf.append("street: ").append(street).append('\n');
		buf.append("city: ").append(city).append('\n');
		buf.append("state: ").append(state).append('\n');
		buf.append("zip: ").append(zip).append('\n');
		buf.append("phone: ").append(phone).append('\n');
		return buf.toString();
	}
	
}
