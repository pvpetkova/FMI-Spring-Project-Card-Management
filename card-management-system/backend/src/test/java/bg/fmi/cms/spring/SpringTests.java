
package bg.fmi.cms.spring;

import bg.fmi.cms.model.User;
import bg.fmi.cms.repo.BinRepository;
import bg.fmi.cms.repo.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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


//    @Autowired
//    UserDetailsService customUserDetails;

//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;


    @Test
    @Rollback(false)
    public void testRepository() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userDetails = userRepository.getByUsername("pesho").get();
        String password = userDetails.getPassword();
        System.out.println("password = " + password);
        System.out.println(passwordEncoder.encode("pesho"));
        Assert.assertTrue(passwordEncoder.matches("pesho", password));
        //$2a$10$n9YMfjLCXoBKd3jN.xLBX.iJnsAPC3RUg.twO8hz4mflbV6k/k13y
//        Assert.assertEquals(password, passwordEncoder.encode("pesho"));
//        System.out.println("passwordEncoder.encode(password) = " + passwordEncoder.encode(password));
    }
}
