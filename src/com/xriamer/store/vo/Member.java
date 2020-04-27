package com.xriamer.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Member implements Serializable {
    private String mid;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String code;
    private Date regdate;
    private String photo;
    private Integer status;
    private List<Orders> allOrders;


    @Override
    public String toString() {
        return "Member{" +
                "mid='" + mid + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", code='" + code + '\'' +
                ", regdate=" + regdate +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Member(String mid, String password, String name, String phone, String address, String code, Date regdate, String photo, Integer status) {
        this.mid = mid;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.code = code;
        this.regdate = regdate;
        this.photo = photo;
        this.status = status;
    }

    public Member() {

    }

    public List<Orders> getAllOrders() {
        return allOrders;
    }
    public void setAllOrders(List<Orders> allOrders){
        this.allOrders=allOrders;
    }
}
