package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/susulink-service/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    private ResponseDTO signUp(@RequestBody RequestBodyDTO dto) {
        return this.userService.signUpForController(dto.name, dto.password);
    }

    @PostMapping("/sign-in")
    private ResponseDTO signIn(@RequestBody RequestBodyDTO dto) {
        return this.userService.signInForController(dto.name, dto.password);
    }

    @PostMapping("/reset-password")
    private ResponseDTO resetPassword(@RequestBody RequestBodyDTO dto) {
        return this.userService.resetPasswordForController(dto.user_id, dto.old_password, dto.new_password);
    }

    private static class RequestBodyDTO {

        public String name;
        public String password;
        public int user_id;
        public String new_password;
        public String old_password;

    }

}
