package bg.fmi.cms.service;

import bg.fmi.cms.model.User;

public interface UserService {
    Iterable<User> getAllUsers();
    User getById(Long id);
    User getByUserName(String userName);
    void delete(Long id);
    void add(User user);
    void update(User user);
}