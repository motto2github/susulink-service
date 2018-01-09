package ink.qwer.susulinkservice.entity;

import java.util.Date;

public class CommonLinkStarEntity {

    private int id;

    private int common_link_id;

    private int user_id;

    private Date create_time;

    private Date update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommon_link_id() {
        return common_link_id;
    }

    public void setCommon_link_id(int common_link_id) {
        this.common_link_id = common_link_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
