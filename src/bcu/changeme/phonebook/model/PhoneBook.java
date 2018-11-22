package bcu.changeme.phonebook.model;

import java.util.*;

/*
 * This class models a phone book, which stores a phone number associated with
 * each name. Names are case-insensitive when used to get, update or remove an
 * entry, but the phone book stores names with their original capitalisation.
 */
public class PhoneBook {
	private final Map<String, PhoneBookEntry> entries = new HashMap<>();
	
	/*
	 * Adds an entry to the phone book, given the name and phone number.
	 * An AlreadyPresentException is thrown if that name is already present.
	 */
	public void addEntry(String name, String phoneNumber) throws AlreadyPresentException {
		String key = name.toUpperCase();
		if(entries.containsKey(key)) {
			throw new AlreadyPresentException(name);
		}
		
		PhoneBookEntry entry = new PhoneBookEntry(name, phoneNumber);
		entries.put(key, entry);
	}
	
	/*
	 * Gets an entry from the phone book, given the name.
	 * A NotPresentException is thrown if that name is not present.
	 */
	public PhoneBookEntry getEntry(String name) throws NotPresentException {
		String key = name.toUpperCase();
		requireKeyExists(key);
		return entries.get(key);
	}
	
	/*
	 * Updates an entry, given the name and a new phone number.
	 * A NotPresentException is thrown if that name is not present.
	 */
	public void updateEntry(String name, String phoneNumber) throws NotPresentException {
		getEntry(name).setPhoneNumber(phoneNumber);
	}
	
	/*
	 * Removes an entry from the phone book, given the name.
	 * A NotPresentException is thrown if that name is not present.
	 */
	public void removeEntry(String name) throws NotPresentException {
		String key = name.toUpperCase();
		requireKeyExists(key);
		entries.remove(key);
	}
	
	/*
	 * Returns an unmodifiable list of all names in the phone book.
	 */
	public List<String> getAllNames() {
		List<String> names = new ArrayList<>();
		for(PhoneBookEntry entry : entries.values()) {
			names.add(entry.getName());
		}
		Collections.sort(names);
		return Collections.unmodifiableList(names);
	}
	
	private void requireKeyExists(String key) throws NotPresentException {
		if(!entries.containsKey(key)) {
			throw new NotPresentException(key);
		}
	}
}
