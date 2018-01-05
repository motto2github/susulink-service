package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.controller.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    private UserEntity getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/user/sign-up")
    private ResponseDTO signUp(String name, String password) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (name == null || "".equals(name) || password == null || "".equals(password)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        boolean existed = this.userService.existName(name);
        if (existed) {
            return responseDTO.set("-1", "该用户名已被注册");
        }
        boolean ok = this.userService.createUser(name, password);
        if (ok) {
            responseDTO.set("1", "注册成功");
        } else {
            responseDTO.set("-1", "注册失败");
        }
        return responseDTO;
    }

    @PostMapping("/user/sign-in")
    private ResponseDTO signIn(String name, String password) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (name == null || "".equals(name) || password == null || "".equals(password)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        UserEntity user = this.userService.getUser(name, password);
        if (user == null) {
            return responseDTO.set("-1", "账号或密码错误");
        }
        this.userService.signIn(user);
        responseDTO.set("1", "登录成功").putDatum("user", user);
        return responseDTO;
    }

}
