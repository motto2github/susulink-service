package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.mapper.UserMapper;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseDTO signUpForController(String name, String password) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (name == null || "".equals(name) || password == null || "".equals(password)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        boolean existed = this.existName(name);
        if (existed) {
            return responseDTO.set("-1", "该用户名已被注册");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        Date now = new Date();
        userEntity.setCreate_time(now);
        userEntity.setUpdate_time(now);
        int effectRows = this.userMapper.insert(userEntity);
        if (effectRows != 1) {
            return responseDTO.set("-1", "注册失败");
        }
        return responseDTO.set("1", "注册成功");
    }

    @Override
    public ResponseDTO signInForController(String name, String password) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (name == null || "".equals(name) || password == null || "".equals(password)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        UserEntity userEntity = this.select(name, password);
        if (userEntity == null) {
            return responseDTO.set("-1", "账号或密码错误");
        }
        int id = userEntity.getId();
        userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUpdate_time(new Date());
        this.userMapper.updateById(userEntity);
        responseDTO.set("1", "登录成功").putDatum("user", userEntity);
        return responseDTO;
    }

    @Override
    public ResponseDTO resetPasswordForController(int userId, String oldPassword, String newPassword) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        UserEntity userEntity = this.select(userId);
        if (userEntity == null) {
            return responseDTO.set("-1", "找不到该用户");
        }
        if (!oldPassword.equals(userEntity.getPassword())) {
            return responseDTO.set("-1", "旧密码不正确");
        }
        userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setPassword(newPassword);
        int effectRows = this.userMapper.updateById(userEntity);
        if (effectRows != 1) {
            return responseDTO.set("-1", "修改失败");
        }
        return responseDTO.set("1", "修改成功");
    }


    @Override
    public UserEntity select(int id) {
        return this.userMapper.selectById(id);
    }

    @Override
    public UserEntity select(String name, String password) {
        return this.userMapper.selectByNameAndPassword(name, password);
    }

    @Override
    public boolean existName(String name) {
        return this.userMapper.countByName(name) > 0;
    }

}
