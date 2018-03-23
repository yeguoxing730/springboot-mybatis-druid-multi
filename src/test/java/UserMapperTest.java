import com.boot.Application;
import com.boot.entity.UserEntity;
import com.boot.enums.UserSexEnum;
import com.boot.mapper.master.MasterUserMapper;
import com.boot.mapper.slave.SlaveUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * Created by yeguoxing on 2018/3/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration
public class UserMapperTest {
    @Autowired
    private MasterUserMapper masterUserMapper;
    @Autowired
    private SlaveUserMapper slaveUserMapper;

    @Test
    public void testInsert() throws Exception {
        masterUserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        masterUserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        masterUserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

//        Assert.assertEquals(3, UserMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = masterUserMapper.getAll();
        System.out.println(users.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        UserEntity user = slaveUserMapper.getOne(Long.valueOf(29));
        System.out.println(user.toString());
        user.setNickName("neo");
        slaveUserMapper.update(user);
//        Assert.assertTrue(("neo".equals(UserMapper.getOne(3l).getNickName())));
    }
}
