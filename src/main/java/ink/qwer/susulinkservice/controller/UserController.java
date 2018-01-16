package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/sign-up")
    private ResponseDTO signUp(String name, String password) {
        return this.userService.signUpForController(name, password);
    }

    @PostMapping("/user/sign-in")
    private ResponseDTO signIn(String name, String password) {
        return this.userService.signInForController(name, password);
    }

    @PostMapping("/user/reset-password")
    private ResponseDTO resetPassword(int userId, String oldPassword, String newPassword) {
        return this.userService.resetPasswordForController(userId, oldPassword, newPassword);
    }

}
