package com.example.hometown.item;

public class FavoritItem {
    private String uid ;
    private String pid ;
    private String addr ;
    private String addr2 ;
    private String year ;
    private String weight;
    private String floor ;
    private String type ;
    private String price;
    private String year2 ;
    private String name ;

    public FavoritItem(String uid, String pid, String addr, String addr2, String year, String weight, String floor, String type, String price, String year2, String name) {
        this.uid = uid;
        this.pid = pid;
        this.addr = addr;
        this.addr2 = addr2;
        this.year = year;
        this.weight = weight;
        this.floor = floor;
        this.type = type;
        this.price = price;
        this.year2 = year2;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}