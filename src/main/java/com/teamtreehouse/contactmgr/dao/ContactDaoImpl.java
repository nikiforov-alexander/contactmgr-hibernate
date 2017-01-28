package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;

import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact findOne(Long id) {
        return null;
    }

    @Override
    public void save(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void saveOrUpdate(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public Contact findLastAdded() {
        return null;
    }

    @Override
    public Long getMaxId() {
        return null;
    }

    @Override
    public void closeDatabase() {

    }
}
