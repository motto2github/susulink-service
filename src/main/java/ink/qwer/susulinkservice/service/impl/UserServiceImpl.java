package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.mapper.UserMapper;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getUser(Integer id) {
        return this.userMapper.selectById(id);
    }

    @Override
    public UserEntity getUser(String name, String password) {
        UserMapper.DTO dto = new UserMapper.DTO();
        dto.name = name;
        dto.password = password;
        return this.userMapper.selectByNameAndPassword(dto);
    }

    @Override
    public boolean createUser(String name, String password) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(password);
        Date cur = new Date();
        user.setCreate_time(cur);
        user.setUpdate_time(cur);
        int effectRows = this.userMapper.insert(user);
        return effectRows > 0;
    }

    @Override
    public boolean existName(String name) {
        UserEntity user = this.userMapper.selectByName(name);
        return user != null;
    }

    @Override
    public void signIn(UserEntity user) {
        user.setName(null);
        user.setPassword(null);
        user.setCreate_time(null);
        user.setUpdate_time(new Date());
        this.userMapper.updateUser(user);
    }
}
