package com.teamtreehouse.contactmgr.dao;

import com.teamtreehouse.contactmgr.model.Contact;
import org.hibernate.Session;
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

    // there is also ton of other ways to do this
    // for an alternative given by @chrisramacciotti,
    // see https://github.com/treehouse/giflib-hibernate/commit/4cd607e5dfde420e2e07d3970f4ede13f4cff783
    @Override
    public List<Contact> findAll() {
        Session session = sessionFactory.openSession();
        List<Contact> contacts = session.createQuery(
                "SELECT c from Contact c",
                Contact.class
        ).list();
        session.close();
        return contacts;
    }

    @Override
    public Contact findOne(Long id) {
        Session session = sessionFactory.openSession();
        Contact contact = session.get(Contact.class, id);
        session.close();
        return contact;
    }

    @Override
    public void save(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Deletes {@code Contact} by given {@code id} as
     * a parameter. Uses HQL to {@code createQuery}
     * with {@code id} as a parameter
     * @param id : id of {@code Contact} to be deleted
     */
    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(
                "DELETE FROM Contact WHERE id = :id"
        ).setParameter("id", id)
        .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Checks if {@code Contact} exists in DAO
     * @param id : id of the task that is checked for
     *           existence
     * @return {@code true} if {@code Contact} exists, and
     * false otherwise
     */
    @Override
    public boolean exists(Long id) {
        Session session = sessionFactory.openSession();
        Long numberOfContactsWithThisId = (Long) session.createQuery(
                "SELECT COUNT(id) FROM Contact WHERE id = :id"
        ).setParameter("id", id)
        .getSingleResult();
        session.close();
        return numberOfContactsWithThisId != 0;
    }

    /**
     * calculates number of tasks in DAO. The SQL
     * function {@code COUNT} is used with {@literal id}
     * column
     * @return : {@code Long count} : number of contacts in DAO
     */
    @Override
    public Long count() {
        Session session = sessionFactory.openSession();
        Long count = (Long) session.createQuery(
                "SELECT COUNT(id) FROM Contact"
        ).getSingleResult();
        session.close();
        return count;
    }

    @Override
    public Contact findLastAdded() {
        return null;
    }

    /**
     * finds max {@code id} of all possible
     * {@code Contact}-s. Used in testing when we
     * add and then remove something, to keep track
     * of the highest added {@code Contact}
     * @return : max {@code id} : the id of latest added contact
     */
    @Override
    public Long getMaxId() {
        Session session = sessionFactory.openSession();
        Long maxId = (Long) session.createQuery(
                "SELECT MAX(id) FROM Contact"
        ).getSingleResult();
        session.close();
        return maxId;
    }

    @Override
    public void closeDatabase() {
        sessionFactory.close();
    }
}
