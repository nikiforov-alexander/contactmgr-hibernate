package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactDaoTest {

    private final String TEST_HIBERNATE_CONFIGURATION_FILE =
            "hibernate-test.cfg.xml";

    private ContactDao contactDao;

    @Before
    public void setUp() throws Exception {
        contactDao =
                new ContactDaoImpl(TEST_HIBERNATE_CONFIGURATION_FILE);
    }

    @After
    public void tearDown() throws Exception {
        contactDao.closeDatabase();
    }

    // private methods that help to set up some tests
    private void addTestContactsToDatabase(int numberOfTestContacts) {
        for (int i = 1; i <= numberOfTestContacts; i++) {
            Contact contact = new Contact.ContactBuilder(
                    "FirstName " + i, "LastName " + i
            ).withEmail("example" + i + "@mail.com")
            .withPhone((long) i)
            .build();
            contactDao.save(contact);
        }
    }

    @Test
    public void contactCanBeSaved() throws Exception {
        // Given testContact to be saved
        // and empty database
        Contact testContact = new Contact();

        // When we save testContact
        contactDao.save(testContact);

        // Then Contact found by id 1, should be equal
        // to testContact
        assertThat(contactDao.findOne(1L))
                .isEqualTo(testContact);
    }

    @Test
    public void contactCanBeUpdated() throws Exception {
        // Given database with 5 test contacts
        addTestContactsToDatabase(5);
        // and updated first contact
        Contact updatedFirstContact = contactDao.findOne(1L);
        updatedFirstContact.setFirstName("new name");

        // When we update firstContact from dao
        contactDao.update(updatedFirstContact);

        // Then returned first contact should be equal to updated one
        assertThat(contactDao.findOne(1L))
                .isEqualTo(updatedFirstContact);
    }

    @Test
    public void findAllReturnsGivenNumberOfContacts() throws Exception {
        // Given dao with 5 test contacts
        addTestContactsToDatabase(5);

        // When we call findAll
        List<Contact> contacts = contactDao.findAll();

        // Then list of 5 tasks should be returned
        assertThat(contacts).hasSize(5);
    }

    @Test
    public void contactCanBeDeleted() throws Exception {
        // Given dao with 5 test Contact
        addTestContactsToDatabase(5);

        // When we deleted first contact
        Contact firstContact = contactDao.findOne(1L);
        contactDao.delete(firstContact);

        // Then we should not be able to find first contact
        assertThat(contactDao.findOne(1L)).isNull();
    }

    @Test
    public void contactCanBeDeletedById() throws Exception {
        // Given dao with 5 test Contact
        addTestContactsToDatabase(5);

        // When we deleted first contact by Id
        contactDao.delete(1L);

        // Then we should not be able to find first contact
        assertThat(contactDao.findOne(1L)).isNull();
    }
}