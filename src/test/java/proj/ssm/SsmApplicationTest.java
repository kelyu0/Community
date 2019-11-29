package proj.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import proj.ssm.dao.UserMapper;
import proj.ssm.entity.User;
import proj.ssm.util.MailClient;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SsmApplication.class)
public class SsmApplicationTest {
    /**
     * DBTest
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindUser(){
        User u= userMapper.selectById(11);
        System.out.println(u.toString());
    }

    @Test
    public void testAddUser(){
        User user =new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());

    }

    /**
     * MailTest
     */
    @Autowired
    MailClient mailClient;
    @Test
    public void sendMailTest(){
        mailClient.sendMail("773830355@qq.com","hello","world");
    }

}
