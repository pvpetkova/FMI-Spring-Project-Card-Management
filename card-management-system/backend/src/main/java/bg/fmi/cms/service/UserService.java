package bg.fmi.cms.service;

import bg.fmi.cms.model.User;
import bg.fmi.cms.model.UserChangeRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(Long id);

    User getByUserName(String userName);

    void delete(Long id);

    void add(User user);

    void addAdm(User user);

    void update(User user);

    void addUserChangeRequest(UserChangeRequest currentUser);

    User getCurrentUser();
}
