package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserLinkMapper {

    @SelectProvider(type = DynamicSQLProvider.class, method = "count")
    int count(DTO dto);

    @SelectProvider(type = DynamicSQLProvider.class, method = "pageSelect")
    List<UserLinkEntity> pageSelect(DTO dto);

    class DTO {

        public Integer userId;
        public String keywords;
        public Integer pageNumber;
        public Integer pageSize;

        private String likeKeywords;
        private Integer beginIndex;

    }

    class DynamicSQLProvider {

        public String count(DTO dto) {
            StringBuilder sb = new StringBuilder(" SELECT COUNT(ul.id) FROM t_user_link ul WHERE TRUE ");
            if (dto.userId != null) {
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
            if (dto.userId != null) {
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
