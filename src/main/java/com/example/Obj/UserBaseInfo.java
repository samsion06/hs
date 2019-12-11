package com.example.Obj;

public class UserBaseInfo {

    private Long id;
    private Long user_id;
    private String  channel_user_id;
    private Long channel_id;
    private String nick_name;
    private String real_name;
    private String mobile_area_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getChannel_user_id() {
        return channel_user_id;
    }

    public void setChannel_user_id(String channel_user_id) {
        this.channel_user_id = channel_user_id;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobile_area_code() {
        return mobile_area_code;
    }

    public void setMobile_area_code(String mobile_area_code) {
        this.mobile_area_code = mobile_area_code;
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", channel_id=" + channel_id +
                ", nick_name='" + nick_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", mobile_area_code='" + mobile_area_code + '\'' +
                '}';
    }
}
