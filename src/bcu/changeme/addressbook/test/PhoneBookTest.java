package bcu.changeme.addressbook.test;

import bcu.changeme.addressbook.model.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

public class PhoneBookTest {
	private final PhoneBook phoneBook = new PhoneBook();
	
	@Test
	public void testAddEntry() throws Exception {
		phoneBook.addEntry("Alice", "12345");
		PhoneBookEntry entry = phoneBook.getEntry("alice");
		assertEquals("12345", entry.getPhoneNumber());
	}
	
	@Test
	public void testUpdateEntry() throws Exception {
		phoneBook.addEntry("Alice", "12345");
		phoneBook.updateEntry("alice", "67890");
		PhoneBookEntry entry = phoneBook.getEntry("alice");
		assertEquals("67890", entry.getPhoneNumber());
	}
	
	@Test
	public void testRemoveEntry() throws Exception {
		phoneBook.addEntry("Alice", "12345");
		phoneBook.removeEntry("alice");
		try {
			phoneBook.getEntry("alice");
			fail();
		} catch(NotPresentException ex) {
			// test passes
		}
	}
	
	@Test
	public void testGetNamesEmpty() throws Exception {
		assertEquals(Collections.emptyList(), phoneBook.getAllNames());
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		phoneBook.addEntry("Alice", "12345");
		phoneBook.addEntry("Charles", "24680");
		phoneBook.addEntry("Bob", "67890");
		assertEquals(
			Arrays.asList("Alice", "Bob", "Charles"),
			phoneBook.getAllNames()
		);
	}
	
	@Test(expected = AlreadyPresentException.class)
	public void testAddEntryAlreadyPresent() throws Exception {
		phoneBook.addEntry("Alice", "12345");
		phoneBook.addEntry("Alice", "67890");
	}
	
	@Test(expected = NotPresentException.class)
	public void testGetEntryNotPresent() throws Exception {
		phoneBook.getEntry("Alice");
	}
	
	@Test(expected = NotPresentException.class)
	public void testUpdateEntryNotPresent() throws Exception {
		phoneBook.updateEntry("Alice", "67890");
	}
	
	@Test(expected = NotPresentException.class)
	public void testRemoveEntryNotPresent() throws Exception {
		phoneBook.removeEntry("Alice");
	}
}
