package bcu.changeme.addressbook.model;

/*
 * This exception is thrown when trying to add an entry to the phone book,
 * but an entry of that name is already present.
 */
public class AlreadyPresentException extends Exception {
	private final String name;
	
	public AlreadyPresentException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
