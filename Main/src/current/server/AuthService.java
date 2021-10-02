package current.server;

import current.server.db.model.User;
import current.server.db.services.UserService;

import java.util.*;

public class AuthService {

    UserService userService = new UserService();

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return userService.findByLoginAndPass(login,password);
    }
}
