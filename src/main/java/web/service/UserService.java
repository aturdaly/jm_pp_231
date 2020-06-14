package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();
    boolean validateUser(String name, String password);
    User getUserById(long id);
    User getUserByName(String name);
    boolean addUser(User user);
    boolean deleteUser(Long id);
    boolean updateUser(User user);
}
