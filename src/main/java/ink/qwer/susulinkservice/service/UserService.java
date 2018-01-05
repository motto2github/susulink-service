package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.UserEntity;

public interface UserService {

    UserEntity getUser(Integer id);

    UserEntity getUser(String name, String password);

    boolean createUser(String name, String password);

    boolean existName(String name);

    void signIn(UserEntity user);

}
