package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommonLinkMapper {

    @Select("SELECT * FROM t_common_link;")
    List<CommonLinkEntity> getAll();

}
