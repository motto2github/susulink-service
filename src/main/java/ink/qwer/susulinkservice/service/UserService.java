package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getUsers();

    UserEntity getUser(Integer id);

}
