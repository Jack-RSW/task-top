package com.jnshu.pojo;

public class Student {
    private long s_id;
    private String s_name;
    private long s_qq;
    private long s_course;
    private String update_at;
    private String s_school;
    private String s_link;
    private String s_flag;
    private long s_brother;
    private String s_source;
    private String create_at;

    public long getS_id() {
        return s_id;
    }

    public void setS_id(long s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public long getS_qq() {
        return s_qq;
    }

    public void setS_qq(long s_qq) {
        this.s_qq = s_qq;
    }

    public long getS_course() {
        return s_course;
    }

    public void setS_course(long s_course) {
        this.s_course = s_course;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getS_school() {
        return s_school;
    }

    public void setS_school(String s_school) {
        this.s_school = s_school;
    }

    public String getS_link() {
        return s_link;
    }

    public void setS_link(String s_link) {
        this.s_link = s_link;
    }

    public String getS_flag() {
        return s_flag;
    }

    public void setS_flag(String s_flag) {
        this.s_flag = s_flag;
    }

    public long getS_brother() {
        return s_brother;
    }

    public void setS_brother(long s_brother) {
        this.s_brother = s_brother;
    }

    public String getS_source() {
        return s_source;
    }

    public void setS_source(String s_source) {
        this.s_source = s_source;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_qq=" + s_qq +
                ", s_course=" + s_course +
                ", update='" + update_at + '\'' +
                ", s_school=" + s_school +
                ", s_link='" + s_link + '\'' +
                ", s_flag='" + s_flag + '\'' +
                ", s_brother=" + s_brother +
                ", s_source='" + s_source + '\'' +
                ", create_at='" + create_at + '\'' +
                '}';
    }
}
