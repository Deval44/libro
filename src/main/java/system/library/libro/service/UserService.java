package system.library.libro.service;

import system.library.libro.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(UUID userId);

    void delete(UUID userId);

}
