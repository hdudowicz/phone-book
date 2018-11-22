package bcu.changeme.phonebook.test;

import bcu.changeme.phonebook.main.Main;
import bcu.changeme.phonebook.model.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MainTest {
	private final PhoneBook phoneBook = new PhoneBook();
	private final Main main = new Main(phoneBook);
	
	@Test
	public void testAddCommand() throws Exception {
		main.parseAndExecute("add Alice 12345");
		PhoneBookEntry entry = phoneBook.getEntry("alice");
		assertEquals("12345", entry.getPhoneNumber());
	}
	
	@Test
	public void testUpdateCommand() throws Exception {
		main.parseAndExecute("add Alice 12345");
		main.parseAndExecute("update alice 67890");
		PhoneBookEntry entry = phoneBook.getEntry("alice");
		assertEquals("67890", entry.getPhoneNumber());
	}
	
	@Test
	public void testRemoveCommand() throws Exception {
		main.parseAndExecute("add Alice 12345");
		main.parseAndExecute("remove alice");
		try {
			phoneBook.getEntry("alice");
			fail();
		} catch(NotPresentException ex) {
			// test passes
		}
	}
	
	@Test(expected = AlreadyPresentException.class)
	public void testAddAlreadyPresent() throws Exception {
		main.parseAndExecute("add Alice 12345");
		main.parseAndExecute("add Alice 67890");
	}
	
	@Test(expected = NotPresentException.class)
	public void testShowNotPresent() throws Exception {
		main.parseAndExecute("show alice");
	}
}
