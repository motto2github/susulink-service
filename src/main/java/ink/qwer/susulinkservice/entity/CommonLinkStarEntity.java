package ink.qwer.susulinkservice.entity;

import java.util.Date;

public class CommonLinkStarEntity {

    private Integer id;

    private Integer common_link_id;

    private Integer user_id;

    private Date create_time;

    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommon_link_id() {
        return common_link_id;
    }

    public void setCommon_link_id(Integer common_link_id) {
        this.common_link_id = common_link_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

}
