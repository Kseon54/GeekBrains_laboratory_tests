package phoneDirectory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDirectory {

    private final ArrayList<Contact> contacts;


    public PhoneDirectory() {
        contacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void add(String surname, String phoneNumber) {
        contacts.add(new Contact(surname, phoneNumber));
    }

    public List<String> get(String surname) {
        return contacts.stream()
                .filter(contact -> contact.getSurname().equals(surname))
                .map(Contact::getPhoneNumber)
                .collect(Collectors.toList());
    }
}
