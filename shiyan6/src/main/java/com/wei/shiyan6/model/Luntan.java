package com.wei.shiyan6.model;

import javax.persistence.*;

@Entity
@Table(name="t_luntan")
public class Luntan
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imgname;
    private String title;
    private String detailmsg;
    private String reviewnum;
    private String posttime;

    public Luntan() {
    }

    public Luntan(String imgname, String title, String detailmsg, String reviewnum, String posttime) {
        this.imgname = imgname;
        this.title = title;
        this.detailmsg = detailmsg;
        this.reviewnum = reviewnum;
        this.posttime = posttime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailmsg() {
        return detailmsg;
    }

    public void setDetailmsg(String detailmsg) {
        this.detailmsg = detailmsg;
    }

    public String getReviewnum() {
        return reviewnum;
    }

    public void setReviewnum(String reviewnum) {
        this.reviewnum = reviewnum;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    @Override
    public String toString() {
        return "Luntan{" +
                "id=" + id +
                ", imgname='" + imgname + '\'' +
                ", title='" + title + '\'' +
                ", detailmsg='" + detailmsg + '\'' +
                ", reviewnum='" + reviewnum + '\'' +
                ", posttime='" + posttime + '\'' +
                '}';
    }
}
