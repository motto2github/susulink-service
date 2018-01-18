package ink.qwer.susulinkservice.mapper;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface UserLinkMapper {

    @Insert("INSERT t_user_link(title, href, summary, icon_url, user_id, create_time, update_time) VALUES(#{title}, #{href}, #{summary}, #{icon_url}, #{user_id}, #{create_time}, #{update_time});")
    int insert(UserLinkEntity userLinkEntity);

    @Delete("DELETE FROM t_user_link WHERE id = #{id};")
    int delete(@Param("id") int id);

    @Select("SELECT ul.* FROM t_user_link ul WHERE ul.title = #{title} AND ul.user_id = #{userId};")
    UserLinkEntity selectByTitleAndUserId(@Param("title") String title, @Param("userId") int userId);

    /**
     * @param dto {userId, keywords, pageNumber, pageSize}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "pageSelect")
    List<UserLinkEntity> pageSelect(DTO dto);

    /**
     * @param dto {userId, keywords}
     * @return
     */
    @SelectProvider(type = DynamicSQLProvider.class, method = "count")
    int count(DTO dto);

    @Select("SELECT COUNT(ul.id) FROM t_user_link ul WHERE ul.user_id = #{userId} AND ul.title = #{title} AND ul.id != #{id}")
    int countForUpdateCheck(@Param("userId") int userId, @Param("title") String title, @Param("id") int id);

    @UpdateProvider(type = DynamicSQLProvider.class, method = "updateById")
    int updateById(UserLinkEntity userLinkEntity);

    @Select("SELECT ul.* FROM t_user_link ul WHERE ul.id = #{id};")
    UserLinkEntity selectById(@Param("id") int id);

    class DTO {

        public int userId;
        public String keywords;
        public int pageNumber;
        public int pageSize;

        private String likeKeywords;
        private int beginIndex;

    }

    // 注意：provider 的方法必须要是 public 的
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
            StringBuilder sb = new StringBuilder(" SELECT ul.* ");

            boolean hasKeywords = dto.keywords != null && !"".equals(dto.keywords);

            if (hasKeywords) {
                dto.likeKeywords = "%" + dto.keywords + "%";
            }

            if (hasKeywords) {
                sb.append(" , ul.title LIKE #{likeKeywords} title_is_like ")
                        .append(" , ul.href LIKE #{likeKeywords} href_is_like ")
                        .append(" , ul.summary LIKE #{likeKeywords} summary_is_like ");
            }

            sb.append(" FROM t_user_link ul WHERE TRUE ");

            if (dto.userId > 0) {
                sb.append(" AND ul.user_id = #{userId} ");
            }
            if (hasKeywords) {
                sb.append(" AND ( ")
                        .append(" ul.title LIKE #{likeKeywords} ")
                        .append(" OR ")
                        .append(" ul.href LIKE #{likeKeywords} ")
                        .append(" OR ")
                        .append(" ul.summary LIKE #{likeKeywords} ")
                        .append(" ) ");
            }

            sb.append(" ORDER BY ");

            if (hasKeywords) {
                sb.append(" title_is_like DESC, ")
                        .append(" href_is_like DESC, ")
                        .append(" summary_is_like DESC, ");
            }

            sb.append(" ul.id ASC ")
                    .append(" LIMIT #{beginIndex}, #{pageSize}; ");

            dto.beginIndex = (dto.pageNumber - 1) * dto.pageSize;
            return sb.toString();
        }

        public String updateById(UserLinkEntity userLinkEntity) {
            StringBuilder sb = new StringBuilder(" UPDATE t_user_link ul SET ul.id = ul.id ");
            String title = userLinkEntity.getTitle();
            if (title != null && !"".equals(title)) {
                sb.append(" , ul.title = #{title} ");
            }
            String href = userLinkEntity.getHref();
            if (href != null && !"".equals(href)) {
                sb.append(" , ul.href = #{href} ");
            }
            sb.append(" , ul.summary = #{summary} ");
            sb.append(" , ul.icon_url = #{icon_url} ");
            Date createTime = userLinkEntity.getCreate_time();
            if (createTime != null) {
                sb.append(" , ul.create_time = #{create_time} ");
            }
            Date updateTime = userLinkEntity.getUpdate_time();
            if (updateTime != null) {
                sb.append(" , ul.update_time = #{update_time} ");
            }
            sb.append(" WHERE ul.id = #{id}; ");
            return sb.toString();
        }

    }

}
