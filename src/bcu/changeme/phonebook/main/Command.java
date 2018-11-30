package bcu.changeme.phonebook.main;

import bcu.changeme.phonebook.model.AlreadyPresentException;
import bcu.changeme.phonebook.model.NotPresentException;
import bcu.changeme.phonebook.model.PhoneBook;

public interface Command {
    public void execute(PhoneBook phoneBook) throws AlreadyPresentException, NotPresentException;

}
