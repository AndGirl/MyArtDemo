package com.ybj.okhttpdemo.model;

import java.io.Serializable;

/**
 * Created by 杨阳洋 on 2017/12/23.
 * 用户结果
 */

public class User implements Serializable {
    private String username;
    private String id;

    private String head_url;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }
}
