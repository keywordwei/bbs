package com.wei.shiyan6.model;

import javax.persistence.*;

@Entity
@Table(name="t_nav")
public class Nav {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String msg;

    public Nav() {
    }

    public Nav(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
