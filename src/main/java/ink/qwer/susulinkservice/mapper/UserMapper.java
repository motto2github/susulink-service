package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Date;

public interface UserMapper {

    @Insert("INSERT t_user(name, password, create_time, update_time) VALUES(#{name}, #{password}, #{create_time}, #{update_time});")
    int insert(UserEntity user);


    @UpdateProvider(type = DynamicSQLProvider.class, method = "updateById")
    int updateById(UserEntity user);


    @Select("SELECT u.* FROM t_user u WHERE u.id = #{id};")
    UserEntity selectById(@Param("id") int id);

    @Select("SELECT u.* FROM t_user u WHERE u.name = #{name} AND u.password = #{password};")
    UserEntity selectByNameAndPassword(@Param("name") String name, @Param("password") String password);


    @Select("SELECT count(u.id) FROM t_user u WHERE u.name = #{name};")
    int countByName(@Param("name") String name);


    class DynamicSQLProvider {

        public String updateById(UserEntity user) {
            StringBuilder sb = new StringBuilder(" UPDATE t_user u SET u.id = u.id ");
            String name = user.getName();
            if (name != null && !"".equals(name)) {
                sb.append(" , u.name = #{name} ");
            }
            String password = user.getPassword();
            if (password != null && !"".equals(password)) {
                sb.append(" , u.password = #{password} ");
            }
            Date createTime = user.getCreate_time();
            if (createTime != null) {
                sb.append(" , u.create_time = #{create_time} ");
            }
            Date updateTime = user.getUpdate_time();
            if (updateTime != null) {
                sb.append(" , u.update_time = #{update_time} ");
            }
            sb.append(" WHERE u.id = #{id}; ");
            return sb.toString();
        }

    }

}
