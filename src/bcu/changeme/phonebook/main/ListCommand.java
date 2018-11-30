package bcu.changeme.phonebook.main;

import bcu.changeme.phonebook.model.NotPresentException;
import bcu.changeme.phonebook.model.PhoneBook;

import java.util.List;

public class ListCommand implements Command {

    public ListCommand(String[] parts) throws InvalidCommandException {
        if(parts.length != 1) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public void execute(PhoneBook phoneBook) {
        List<String> names = phoneBook.getAllNames();
        if(names.isEmpty()) {
            System.out.println("No entries.");
        } else {
            for(String name : names) {
                System.out.println(name);
            }
        }
    }
}