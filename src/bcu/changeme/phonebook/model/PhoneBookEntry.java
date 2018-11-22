package bcu.changeme.phonebook.model;

/*
 * Models a single entry in the phone book, with a name and a phone number.
 */
public class PhoneBookEntry {
	private final String name;
	private String phoneNumber;
	
	public PhoneBookEntry(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
