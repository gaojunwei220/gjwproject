package net.practise.pojo;

import java.util.Date;

/**
 * Created by gjw on 2018/7/26.
 */
public class User {

    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private int articles;
    private int videos;
    private String avatar;
    private String info;
    private int role_id;
    private int miss_number;
    private Date miss_time;
    private Date allow_time;

    public User() {
    }

    public User(int id, String name, String password, String email, String phone, int articles, int videos, String avatar, String info, int role_id, int miss_number, Date miss_time, Date allow_time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.articles = articles;
        this.videos = videos;
        this.avatar = avatar;
        this.info = info;
        this.role_id = role_id;
        this.miss_number = miss_number;
        this.miss_time = miss_time;
        this.allow_time = allow_time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getArticles() {
        return articles;
    }

    public void setArticles(int articles) {
        this.articles = articles;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getMiss_number() {
        return miss_number;
    }

    public void setMiss_number(int miss_number) {
        this.miss_number = miss_number;
    }

    public Date getMiss_time() {
        return miss_time;
    }

    public void setMiss_time(Date miss_time) {
        this.miss_time = miss_time;
    }

    public Date getAllow_time() {
        return allow_time;
    }

    public void setAllow_time(Date allow_time) {
        this.allow_time = allow_time;
    }
}
