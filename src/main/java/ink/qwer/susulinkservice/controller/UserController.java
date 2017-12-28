package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/all")
    private List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    @GetMapping("/user/{id}")
    private UserEntity getUserById(@PathVariable Integer id) {
        return userMapper.getById(id);
    }

}
