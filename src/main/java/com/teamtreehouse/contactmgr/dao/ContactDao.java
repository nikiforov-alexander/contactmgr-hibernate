package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;

import java.util.List;

public interface ContactDao {

    // standard methods

    List<Contact> findAll();

    Contact findOne(Long id);

    void save(Contact contact);
    void update(Contact contact);
    void saveOrUpdate(Contact contact);

    void delete(Contact contact);
    void delete(Long id);

    // helpful methods

    boolean exists(Long id);

    Long count();

    Contact findLastAdded();

    Long getMaxId();

    void closeDatabase();
}
