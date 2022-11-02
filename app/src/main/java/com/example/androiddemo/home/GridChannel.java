package com.example.androiddemo.home;

/**
 * 网格适配通道参数
 */
public class GridChannel {
    private int id;
    private int imgId;
    private String desc;
    private Class cls;

    public GridChannel(int id, int imgId, String desc, Class cls) {
        this.id = id;
        this.imgId = imgId;
        this.desc = desc;
        this.cls = cls;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "GridChannel{" +
                "id=" + id +
                ", imgId=" + imgId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
