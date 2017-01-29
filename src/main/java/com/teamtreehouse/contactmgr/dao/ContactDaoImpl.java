package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private final SessionFactory sessionFactory;

    /**
     * build Hibernate's sessionFactory with
     * provided in constructor {@literal hibernateConfigurationFile}
     * see <a href="http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native-metadata">
     *     Hibernate User's Guide 5.2
     *     </a>
     * @param hibernateConfigurationFile : {@literal String} that
     *                                   simply provides configuration
     *                                   file name. It will be used in
     *                                   for testing purposes. Default
     *                                   is "hibernate.cfg.xml"
     * @return {@literal SessionFactory} with which we can
     * open sessions and make transactions
     */
    private SessionFactory
    buildSessionFactory(String hibernateConfigurationFile) {
        final ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure(
                        hibernateConfigurationFile
                ).build();
        return new MetadataSources(serviceRegistry)
                .buildMetadata()
                .buildSessionFactory();
    }

    // constructors

    // this kind of constructor is especially helpful in
    // Unit Testing, because by changing config we can
    // easily switch databases
    public ContactDaoImpl(String hibernateConfigurationFile) {
        this.sessionFactory = buildSessionFactory(
                hibernateConfigurationFile
        );
    }

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
        sessionFactory.close();
    }
}
