package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

}