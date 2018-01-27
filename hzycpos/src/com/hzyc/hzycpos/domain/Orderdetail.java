package com.hzyc.hzycpos.domain;

public class Orderdetail {
    private Integer id;

    private String ordersCode;

    private String goodCode;

    private String number;

    private String revenueMoney;

    private String goodSkind;

    private String remark;

    private String p1;

    private String p2;

    private String p3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdersCode() {
        return ordersCode;
    }

    public void setOrdersCode(String ordersCode) {
        this.ordersCode = ordersCode == null ? null : ordersCode.trim();
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getRevenueMoney() {
        return revenueMoney;
    }

    public void setRevenueMoney(String revenueMoney) {
        this.revenueMoney = revenueMoney == null ? null : revenueMoney.trim();
    }

    public String getGoodSkind() {
        return goodSkind;
    }

    public void setGoodSkind(String goodSkind) {
        this.goodSkind = goodSkind == null ? null : goodSkind.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1 == null ? null : p1.trim();
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2 == null ? null : p2.trim();
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3 == null ? null : p3.trim();
    }
}