package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public boolean validateUser(String name, String password) {
        String hql = "from User where name = :paramName AND password = :paramPassword";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramName", name);
        query.setParameter("paramPassword", password);
        User user = (User) query.getSingleResult();
        return user!=null;
    }

    @Override
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        String hql = "from User where name = :paramName";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramName", name);
        return (User) query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
