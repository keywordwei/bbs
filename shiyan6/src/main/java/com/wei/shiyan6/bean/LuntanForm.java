package com.wei.shiyan6.bean;

public class LuntanForm {
    private String title;
    private String msg;
    private String username;
    private Long updateId;

    public LuntanForm() {
    }

    public LuntanForm(String title, String msg, String username) {
        this.title = title;
        this.msg = msg;
        this.username = username;
    }

    public LuntanForm(String title, String msg, String username, Long updateId) {
        this.title = title;
        this.msg = msg;
        this.username = username;
        this.updateId = updateId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
