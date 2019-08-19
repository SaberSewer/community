package online.cangjie.comumit;

import online.cangjie.comumit.interfaces.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComumitApplicationTests {
    @Autowired
    private IndexService indexService;

    @Test
    public void contextLoads() {
        System.out.println(indexService.getHotCat());
    }

}
