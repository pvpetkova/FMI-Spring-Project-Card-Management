package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.User;
import bg.fmi.cms.model.constats.AccountStatus;
import bg.fmi.cms.repo.UserRepository;
import bg.fmi.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUserName(String userName) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void add(User user) {
        user.setAccountStatus(AccountStatus.PENDING);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {

    }
}
