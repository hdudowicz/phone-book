package bcu.changeme.phonebook.model;

/*
 * This exception is thrown when trying to get, update or remove an entry from
 * the phone book, but no entry of that name exists.
 */
public class NotPresentException extends Exception {
	private final String name;
	
	public NotPresentException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
