package proj.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.ssm.dao.UserMapper;
import proj.ssm.entity.User;
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findById(int id){
        return userMapper.selectById(id);
    }

    public int addUser(User user) {
        return userMapper.insertUser(user);
    }
}
