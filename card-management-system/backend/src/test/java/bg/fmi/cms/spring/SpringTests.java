package bg.fmi.cms.spring;

import bg.fmi.cms.repo.BinRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringTests {

    @Autowired
    BinRepository binRepository;

    @Test
    public void testRepository(){
        Assert.assertNotNull(binRepository);
    }
}
