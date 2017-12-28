package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLinkMapper {

    @Select("SELECT * FROM t_user_link;")
    List<UserLinkEntity> getAll();

}
