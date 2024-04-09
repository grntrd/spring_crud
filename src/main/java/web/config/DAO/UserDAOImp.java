package web.config.DAO;

import org.springframework.stereotype.Repository;
import web.config.models.User;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(long id, User updateUser) {
        User userToBeUpdated  = getById(id);
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setLastName(updateUser.getLastName());
        userToBeUpdated.setAge(updateUser.getAge());
        entityManager.merge(userToBeUpdated);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getById(id));
    }

}
