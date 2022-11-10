package com.example.androiddemo.media;

/**
 * Media Fragment GridView Item
 */
public class MediaGridItem {
    private int id;
    private int imgId;
    private String title;
    private Class cls;

    public MediaGridItem(int id, int imgId, String title, Class cls) {
        this.id = id;
        this.imgId = imgId;
        this.title = title;
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
