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
        public Integer pageNumber;
        public Integer pageSize;

        private String likeKeywords;
        private Integer beginIndex;

    }

    class DynamicSQLProvider {

        public String count(DTO dto) {
            StringBuilder sb = new StringBuilder(" SELECT COUNT(cl.id) FROM t_common_link cl WHERE TRUE ");
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sb.append(" AND ( cl.title LIKE #{likeKeywords} OR cl.href LIKE #{likeKeywords} OR cl.summary LIKE #{likeKeywords} ) ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sb.append(" ; ");
            return sb.toString();
        }

        public String pageSelect(DTO dto) {
            StringBuilder sql = new StringBuilder(" SELECT cl.* FROM t_common_link cl WHERE TRUE ");
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sql.append(" AND ( cl.title LIKE #{likeKeywords} OR cl.href LIKE #{likeKeywords} OR cl.summary LIKE #{likeKeywords} ) ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sql.append(" LIMIT #{beginIndex}, #{pageSize}; ");
            dto.beginIndex = (dto.pageNumber - 1) * dto.pageSize;
            return sql.toString();
        }

    }

}



