package com.hzyc.hzycpos.domain;

public class VipRecord {
    private Integer id;

    private String code;

    private String type;

    private String money;

    private String giveMoney;

    private String operateDate;

    private String giveGood;

    private String isReceive;

    private String lingqudate;

    private String integral;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(String giveMoney) {
        this.giveMoney = giveMoney == null ? null : giveMoney.trim();
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate == null ? null : operateDate.trim();
    }

    public String getGiveGood() {
        return giveGood;
    }

    public void setGiveGood(String giveGood) {
        this.giveGood = giveGood == null ? null : giveGood.trim();
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive == null ? null : isReceive.trim();
    }

    public String getLingqudate() {
        return lingqudate;
    }

    public void setLingqudate(String lingqudate) {
        this.lingqudate = lingqudate == null ? null : lingqudate.trim();
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral == null ? null : integral.trim();
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