package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
//        return entityManager.createNativeQuery("SELECT * FROM kataacademy.users", User.class).getResultList();
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void createUser(User user) {
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(Long id, String[] params) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);


    }

    @Override
    public void deleteUser(Long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
