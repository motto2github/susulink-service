package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserLinkMapper {

    @Insert("INSERT t_user_link(title, href, summary, icon_url, user_id, create_time, update_time) VALUES(#{title}, #{href}, #{summary}, #{icon_url}, #{user_id}, #{create_time}, #{update_time});")
    int insert(UserLinkEntity userLinkEntity);


    /**
     * @param dto {userId, keywords}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "count")
    int count(DTO dto);

    /**
     * @param dto {userId, keywords, pageNumber, pageSize}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "pageSelect")
    List<UserLinkEntity> pageSelect(DTO dto);

    @Select("SELECT ul.* FROM t_user_link ul WHERE ul.title = #{title} AND ul.user_id = #{userId};")
    UserLinkEntity selectByTitleAndUserId(@Param("title") String title, @Param("userId") int userId);


    class DTO {

        public int userId;
        public String keywords;
        public int pageNumber;
        public int pageSize;

        private String likeKeywords;
        private int beginIndex;

    }

    class DynamicSQLProvider {

        public String count(DTO dto) {
            StringBuilder sb = new StringBuilder(" SELECT COUNT(ul.id) FROM t_user_link ul WHERE TRUE ");
            if (dto.userId > 0) {
                sb.append(" AND ul.user_id = #{userId} ");
            }
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sb.append(" AND ( ul.title LIKE #{likeKeywords} OR ul.href LIKE #{likeKeywords} OR ul.summary LIKE #{likeKeywords} ) ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sb.append(" ; ");
            return sb.toString();
        }

        public String pageSelect(DTO dto) {
            StringBuilder sb = new StringBuilder(" SELECT ul.* FROM t_user_link ul WHERE TRUE ");
            if (dto.userId > 0) {
                sb.append(" AND ul.user_id = #{userId} ");
            }
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sb.append(" AND ( ul.title LIKE #{likeKeywords} OR ul.href LIKE #{likeKeywords} OR ul.summary LIKE #{likeKeywords} ) ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sb.append(" LIMIT #{beginIndex}, #{pageSize}; ");
            dto.beginIndex = (dto.pageNumber - 1) * dto.pageSize;
            return sb.toString();
        }

    }

}
