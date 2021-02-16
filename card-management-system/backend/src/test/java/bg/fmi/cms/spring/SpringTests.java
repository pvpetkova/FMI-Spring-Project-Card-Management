
package bg.fmi.cms.spring;

import bg.fmi.cms.model.User;
import bg.fmi.cms.repo.BinRepository;
import bg.fmi.cms.repo.RequestRepository;
import bg.fmi.cms.repo.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringTests {

    @Autowired
    BinRepository binRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestRepository requestRepository;


//    @Autowired
//    UserDetailsService customUserDetails;

//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;


    @Test
    @Rollback(false)
    public void testRepository() {
        Iterable<User> all = userRepository.findAll();
        all.forEach(u -> System.out.println("id1234"+ u.getId()));
        //$2a$10$n9YMfjLCXoBKd3jN.xLBX.iJnsAPC3RUg.twO8hz4mflbV6k/k13y
//        Assert.assertEquals(password, passwordEncoder.encode("pesho"));
//        System.out.println("passwordEncoder.encode(password) = " + passwordEncoder.encode(password));
    }
}
