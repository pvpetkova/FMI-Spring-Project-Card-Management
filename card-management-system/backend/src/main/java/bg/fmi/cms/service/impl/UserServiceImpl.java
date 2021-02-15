package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.User;
import bg.fmi.cms.model.constats.AccountStatus;
import bg.fmi.cms.repo.UserRepository;
import bg.fmi.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

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
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAccountStatus(AccountStatus.PENDING);
        if (userRepository == null) {
            throw new RuntimeException("user repo is null");
        }
        userRepository.save(user);
    }

    @Override
    public void update(User user) {

    }
}
