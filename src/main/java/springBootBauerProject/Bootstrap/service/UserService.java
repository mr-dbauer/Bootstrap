package springBootBauerProject.Bootstrap.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import springBootBauerProject.Bootstrap.models.User;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    void updateUser(User user);

    void updateUserWithoutPassword(User user);

    void deleteUserById(long id);

    User getUsersById(long id);

    void addUser(User user);

    UserDetails loadUserByUsername(String username);

    User getAuthenticatedUser();
}
