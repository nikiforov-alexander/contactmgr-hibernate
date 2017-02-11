package com.teamtreehouse.contactmgr;

import com.teamtreehouse.contactmgr.dao.ContactDao;
import com.teamtreehouse.contactmgr.dao.ContactDaoImpl;
import com.teamtreehouse.contactmgr.model.Contact;
import com.teamtreehouse.contactmgr.model.Contact.ContactBuilder;


public class Application {

    public static void main(String[] args) {
        // create dao that encapsulates all database interaction logic
        ContactDao contactDao = new ContactDaoImpl("hibernate.cfg.xml");

        // save contact
        Contact contact = new ContactBuilder("Chris","Ramacciotti")
                .withEmail("rama@teamtreehouse.com")
                .withPhone(7735556666L)
                .build();
        contactDao.save(contact);
        Long id = contactDao.getMaxId();

        // Display a list of contacts before the update
        System.out.printf("%n%nBefore update%n%n");
        contactDao.findAll().forEach(System.out::println);

        // Get the persisted contact
        Contact c = contactDao.findOne(id);

        // Update the contact
        c.setFirstName("Beatrix");

        // Persist the changes
        System.out.printf("%nUpdating...%n");
        contactDao.update(c);
        System.out.printf("%nUpdate complete!%n");

        // Display a list of contacts after the update
        System.out.printf("%nAfter update%n");
        contactDao.findAll().forEach(System.out::println);

        // Get the last added contact
        c = contactDao.findLastAdded();

        // Delete the contact
        System.out.printf("%nDeleting...%n");
        contactDao.delete(c);
        System.out.printf("%nDeleted!%n");
        System.out.printf("%nAfter delete%n");
        contactDao.findAll().forEach(System.out::println);

        // close the database
        contactDao.closeDatabase();
    }
}
