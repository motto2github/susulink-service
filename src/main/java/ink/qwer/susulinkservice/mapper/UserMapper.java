package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM t_user;")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM t_user u WHERE u.id = #{id};")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    UserEntity getById(Integer id);

}
