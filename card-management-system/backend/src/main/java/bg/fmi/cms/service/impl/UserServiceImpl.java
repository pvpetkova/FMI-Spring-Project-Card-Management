package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.User;
import bg.fmi.cms.model.UserChangeRequest;
import bg.fmi.cms.model.UserDetailsImpl;
import bg.fmi.cms.model.constats.AccountStatus;
import bg.fmi.cms.repo.UserChangeRequestRepository;
import bg.fmi.cms.repo.UserRepository;
import bg.fmi.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserChangeRequestRepository userChangeRequestRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User getByUserName(String userName) {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(user1 -> userRepository.delete(user1));
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
//        Optional<User> byId = userRepository.findById(user.getId());
//        if (byId.isPresent()) {
//            User userFromDb = byId.get();
//            userFromDb.setAccountStatus(user.getAccountStatus());
//            userFromDb.setRole(user.getRole());
//            userFromDb.setUsername(user.getUsername());
//
//            userRepository.save(userFromDb);
//        }
        userRepository.save(user);
    }

    @Override
    public void addUserChangeRequest(UserChangeRequest currentUser) {
        userChangeRequestRepository.save(currentUser);
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getUser();
        } else {
            return null;
        }
    }
}
