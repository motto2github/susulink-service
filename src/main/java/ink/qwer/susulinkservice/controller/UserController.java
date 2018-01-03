package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    private List<UserEntity> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    private UserEntity getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }



}
