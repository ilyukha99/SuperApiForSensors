package org.sas;

import org.sas.dao.UserDAO;
import org.sas.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userDAO;
    private SessionFactory sessionFactory;
    private final User testUser = new User();
    private final Integer primaryKey = 100500;

    @Before
    public void doBefore() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        userDAO = new UserDAO(sessionFactory);
        testUser.setToken("qwerty");
        testUser.setPassword("12345");
        testUser.setTimezone(18);
        testUser.setLogin("test");
    }

    @After
    public void doAfter() {
        if (userDAO.read(primaryKey) != null) {
            userDAO.delete(testUser);
        }
        sessionFactory.close();
    }

    /**
     * @see org.sas.dao.UserDAO#create(User)
     * @see org.sas.dao.UserDAO#read(Integer)
     */
    @Test
    public void checkCreatedUser() {
        int id = userDAO.create(testUser);
        final User user = userDAO.read(id);
        assertNotNull(user);
        assertEquals(user, testUser);
    }

    /**
     * @see org.sas.dao.UserDAO#delete(User)
     */
    @Test
    public void checkDeletions() {
        int id = userDAO.create(testUser);
        final User userBeforeDeletion = userDAO.read(id);
        assertNotNull(userBeforeDeletion);
        userDAO.delete(testUser);
        final User userAfterDeletion = userDAO.read(id);
        assertNull(userAfterDeletion);
    }

    /**
     * @see org.sas.dao.UserDAO#update(User)
     */
    @Test
    public void checkUpdates() {
        int id = userDAO.create(testUser);
        testUser.setLogin("NewLogin");
        testUser.setPassword("NewPassword");
        userDAO.update(testUser);
        final User user = userDAO.read(id);
        assertNotNull(user);
        assertTrue(user.getLogin().equals("NewLogin") && user.getPassword().equals("NewPassword"));
    }
}
