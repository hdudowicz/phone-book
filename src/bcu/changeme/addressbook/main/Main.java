package bcu.changeme.addressbook.main;

import bcu.changeme.addressbook.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
	public static final String HELP_MESSAGE
		= "Commands:\n" +
		"    add [name] [phoneNumber]        add a new entry\n" +
		"    show [name]                     show an entry\n" +
		"    update [name] [phoneNumber]     update an entry\n" +
		"    remove [name]                   remove an entry\n" +
		"    list                            show all names\n" +
		"    help                            show this help message\n" +
		"    exit                            exit the program";
	
	public static void main(String[] args) throws IOException {
		new Main().run();
	}
	
	private final PhoneBook phoneBook;
	
	public Main() {
		this.phoneBook = new PhoneBook();
	}
	
	public Main(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}
	
	public void run() throws IOException {
		BufferedReader keyboard = new BufferedReader(
			new InputStreamReader(System.in)
		);
		
		System.out.println("Address book");
		while(true) {
			System.out.print("> ");
			String command = keyboard.readLine();
			if("exit".equalsIgnoreCase(command)) {
				break;
			}
			
			try {
				parseAndExecute(command);
			} catch(AlreadyPresentException ex) {
				System.out.println("The entry for " + ex.getName() + " already exists.");
			} catch(NotPresentException ex) {
				System.out.println("The entry for " + ex.getName() + " does not exist.");
			} catch(InvalidCommandException ex) {
				System.out.println("Invalid command (enter 'help' to see the valid commands).");
			}
		}
	}
	
	public void parseAndExecute(String command) throws AlreadyPresentException, NotPresentException, InvalidCommandException {
		String[] parts = command.split(" ");
		String firstPart = parts[0];
		
		if("add".equalsIgnoreCase(firstPart)) {
			if(parts.length != 3) {
				throw new InvalidCommandException();
			}
			phoneBook.addEntry(parts[1], parts[2]);
			System.out.println("Entry added.");
		} else if("show".equalsIgnoreCase(firstPart)) {
			if(parts.length != 2) {
				throw new InvalidCommandException();
			}
			PhoneBookEntry entry = phoneBook.getEntry(parts[1]);
			System.out.println("Name: " + entry.getName());
			System.out.println("Phone number: " + entry.getPhoneNumber());
		} else if("update".equalsIgnoreCase(firstPart)) {
			if(parts.length != 3) {
				throw new InvalidCommandException();
			}
			phoneBook.updateEntry(parts[1], parts[2]);
			System.out.println("Entry updated.");
		} else if("remove".equalsIgnoreCase(firstPart)) {
			if(parts.length != 2) {
				throw new InvalidCommandException();
			}
			phoneBook.removeEntry(parts[1]);
			System.out.println("Entry removed.");
		} else if("list".equalsIgnoreCase(firstPart)) {
			if(parts.length != 1) {
				throw new InvalidCommandException();
			}
			List<String> names = phoneBook.getAllNames();
			if(names.isEmpty()) {
				System.out.println("No entries.");
			} else {
				for(String name : names) {
					System.out.println(name);
				}
			}
		} else if("help".equalsIgnoreCase(firstPart)) {
			if(parts.length != 1) {
				throw new InvalidCommandException();
			}
			System.out.println(HELP_MESSAGE);
		} else {
			throw new InvalidCommandException();
		}
	}
}
