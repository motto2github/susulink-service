package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM t_user;")
    List<UserEntity> getAll();

    @Select("SELECT * FROM t_user u WHERE u.id = #{id};")
    UserEntity getOne(Integer id);

}
