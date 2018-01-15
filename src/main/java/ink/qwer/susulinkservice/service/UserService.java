package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserEntity;

public interface UserService {

    ResponseDTO signUpForController(String name, String password);

    ResponseDTO signInForController(String name, String password);

    ResponseDTO resetPasswordForController(int userId, String oldPassword, String newPassword);


    UserEntity select(int id);

    UserEntity select(String name, String password);

    boolean existName(String name);

}
