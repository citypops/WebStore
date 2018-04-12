package citypops.webstore.service;

import citypops.webstore.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(String username);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String username);
}
