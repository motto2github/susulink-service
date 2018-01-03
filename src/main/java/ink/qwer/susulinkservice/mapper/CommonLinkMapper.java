package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CommonLinkMapper {

    @SelectProvider(type = DynamicSQLProvider.class, method = "count")
    int count(DTO dto);

    @SelectProvider(type = DynamicSQLProvider.class, method = "pageSelect")
    List<CommonLinkEntity> pageSelect(DTO dto);

    class DTO {

        public String keywords;
        private String likeKeywords;
        public Integer pageNumber;
        public Integer pageSize;
        private Integer beginIndex;

    }

    class DynamicSQLProvider {

        public String count(DTO dto) {
            if (dto.keywords == null || "".equals(dto.keywords)) {
                return "SELECT count(id) FROM t_common_link;";
            }
            dto.likeKeywords = "%" + dto.keywords + "%";
            return "SELECT count(id) FROM t_common_link WHERE title LIKE #{likeKeywords} OR href LIKE #{likeKeywords} OR summary LIKE #{likeKeywords};";
        }

        public String pageSelect(DTO dto) {
            StringBuilder sql = new StringBuilder(" SELECT * FROM t_common_link ");
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sql.append(" WHERE title LIKE #{likeKeywords} OR href LIKE #{likeKeywords} OR summary LIKE #{likeKeywords} ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sql.append(" LIMIT #{beginIndex}, #{pageSize}; ");
            dto.beginIndex = (dto.pageNumber - 1) * dto.pageSize;
            return sql.toString();
        }

    }

}



