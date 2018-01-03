package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.mapper.UserMapper;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUsers() {
        return userMapper.getAll();
    }

    @Override
    public UserEntity getUser(Integer id) {
        return userMapper.getOne(id);
    }

}
