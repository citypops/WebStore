package citypops.webstore.persistence;

import citypops.webstore.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();
    User getUserById(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
}
