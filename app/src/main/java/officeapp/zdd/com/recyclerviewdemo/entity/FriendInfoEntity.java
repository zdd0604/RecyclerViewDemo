package officeapp.zdd.com.recyclerviewdemo.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/7/19.
 * 朋友的列表信息
 */

public class FriendInfoEntity implements Serializable {
    private static final long serialVersionUID = 6482907417728082118L;
    private int titleID;
    private String name;
    private String phoneNumber;
    private String address;
    private boolean isOver;//是否是最后一条数据
    private boolean isDelete;//是否删除

    public FriendInfoEntity() {
    }

    public FriendInfoEntity(int titleID, String name, String phoneNumber, String address, boolean isOver) {
        this.titleID = titleID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isOver = isOver;
    }

    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
