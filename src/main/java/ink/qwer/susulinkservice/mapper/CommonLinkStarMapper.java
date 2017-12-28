package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.CommonLinkStarEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommonLinkStarMapper {

    @Select("SELECT * FROM t_common_link_star;")
    List<CommonLinkStarEntity> getAll();

}
