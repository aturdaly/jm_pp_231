package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateUser(String name, String password) {
        return userDao.validateUser(name, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        userDao.addUser(user);
        return validateUser(user.getName(), user.getPassword());
    }

    @Transactional
    @Override
    public boolean deleteUser(Long id) {
        User user = userDao.getUserById(id);
        userDao.deleteUser(user);
        return true;
    }

    @Transactional
    @Override
    public boolean updateUser(User user) {
        userDao.updateUser(user);
        return validateUser(user.getName(), user.getPassword());
    }
}