package com.devidea.grigoapplication;

import java.util.Arrays;

public class UserDataDTO{

    private String email;
    private String name;
    private Integer student_id;
    private String phone;
    private String birth;
    private String sex;
    private String[] tags;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {

        this.student_id = student_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTags() {
        return Arrays.toString(tags);
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

}
