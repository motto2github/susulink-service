package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CommonLinkMapper {

    /**
     * @param dto {keywords, pageNumber, pageSize}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "pageSelect")
    List<CommonLinkEntity> pageSelect(DTO dto);

    /**
     * @param dto {keywords}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "count")
    int count(DTO dto);

    class DTO {

        public String keywords;
        public int pageNumber;
        public int pageSize;

        private String likeKeywords;
        private int beginIndex;

    }

    class DynamicSQLProvider {

        public String pageSelect(DTO dto) {
            StringBuilder sql = new StringBuilder(" SELECT cl.* ");

            boolean hasKeywords = dto.keywords != null && !"".equals(dto.keywords);

            if (hasKeywords) {
                dto.likeKeywords = "%" + dto.keywords + "%";
            }

            if (hasKeywords) {
                sql.append(" , cl.title LIKE #{likeKeywords} title_is_like ")
                        .append(" , cl.href LIKE #{likeKeywords} href_is_like ")
                        .append(" , cl.summary LIKE #{likeKeywords} summary_is_like ");
            }

            sql.append(" FROM t_common_link cl WHERE TRUE ");

            if (hasKeywords) {
                sql.append(" AND ( ")
                        .append(" cl.title LIKE #{likeKeywords} ")
                        .append(" OR ")
                        .append(" cl.href LIKE #{likeKeywords} ")
                        .append(" OR ")
                        .append(" cl.summary LIKE #{likeKeywords} ")
                        .append(" ) ");
            }

            sql.append(" ORDER BY ");

            if (hasKeywords) {
                sql.append(" title_is_like DESC, ")
                        .append(" href_is_like DESC, ")
                        .append(" summary_is_like DESC, ");
            }

            sql.append(" cl.id DESC ")
                    .append(" LIMIT #{beginIndex}, #{pageSize}; ");

            dto.beginIndex = (dto.pageNumber - 1) * dto.pageSize;
            return sql.toString();
        }

        public String count(DTO dto) {
            StringBuilder sb = new StringBuilder(" SELECT COUNT(cl.id) FROM t_common_link cl WHERE TRUE ");
            if (dto.keywords != null && !"".equals(dto.keywords)) {
                sb.append(" AND ( cl.title LIKE #{likeKeywords} OR cl.href LIKE #{likeKeywords} OR cl.summary LIKE #{likeKeywords} ) ");
                dto.likeKeywords = "%" + dto.keywords + "%";
            }
            sb.append(" ; ");
            return sb.toString();
        }

    }

}



