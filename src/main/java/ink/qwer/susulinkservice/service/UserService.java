package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.UserEntity;

public interface UserService {

    UserEntity getUser(int id);

    UserEntity getUser(String name, String password);

    boolean createUser(String name, String password);

    boolean existName(String name);

    void signIn(int id);

    boolean resetPassword(int id, String newPassword);

}
