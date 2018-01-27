package com.hzyc.hzycpos.domain;


public class Vip {
    private Integer id;

    private String hyCode;

    private Integer hyKindId;

    private String hyTel;

    private String hyName;

    private String hyCard;

    private double hyTotal;

    private double hyRemainMoney;

    private String hyBirthday;

    private String hyCunt;

    private String hyBz;

    private String hyJf;

    private String hyDate;

    private double hyExpense;

    private String hyAddress;

    private String remark;

    private String p1;

    private String p2;

    private String p3;

    private byte[] hyPhoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHyCode() {
        return hyCode;
    }

    public void setHyCode(String hyCode) {
        this.hyCode = hyCode == null ? null : hyCode.trim();
    }

    public Integer getHyKindId() {
        return hyKindId;
    }

    public void setHyKindId(Integer hyKindId) {
        this.hyKindId = hyKindId;
    }

    public String getHyTel() {
        return hyTel;
    }

    public void setHyTel(String hyTel) {
        this.hyTel = hyTel == null ? null : hyTel.trim();
    }

    public String getHyName() {
        return hyName;
    }

    public void setHyName(String hyName) {
        this.hyName = hyName == null ? null : hyName.trim();
    }

    public String getHyCard() {
        return hyCard;
    }

    public void setHyCard(String hyCard) {
        this.hyCard = hyCard == null ? null : hyCard.trim();
    }

    public double getHyTotal() {
        return hyTotal;
    }

    public void setHyTotal(double hyTotal) {
        this.hyTotal = hyTotal;
    }

    public double getHyRemainMoney() {
        return hyRemainMoney;
    }

    public void setHyRemainMoney(double hyRemainMoney) {
        this.hyRemainMoney = hyRemainMoney;
    }

    public String getHyBirthday() {
        return hyBirthday;
    }

    public void setHyBirthday(String hyBirthday) {
        this.hyBirthday = hyBirthday == null ? null : hyBirthday.trim();
    }

    public String getHyCunt() {
        return hyCunt;
    }

    public void setHyCunt(String hyCunt) {
        this.hyCunt = hyCunt == null ? null : hyCunt.trim();
    }

    public String getHyBz() {
        return hyBz;
    }

    public void setHyBz(String hyBz) {
        this.hyBz = hyBz == null ? null : hyBz.trim();
    }

    public String getHyJf() {
        return hyJf;
    }

    public void setHyJf(String hyJf) {
        this.hyJf = hyJf == null ? null : hyJf.trim();
    }

    public String getHyDate() {
        return hyDate;
    }

    public void setHyDate(String hyDate) {
        this.hyDate = hyDate == null ? null : hyDate.trim();
    }

    public double getHyExpense() {
        return hyExpense;
    }

    public void setHyExpense(double hyExpense) {
        this.hyExpense = hyExpense;
    }

    public String getHyAddress() {
        return hyAddress;
    }

    public void setHyAddress(String hyAddress) {
        this.hyAddress = hyAddress == null ? null : hyAddress.trim();
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

    public byte[] getHyPhoto() {
        return hyPhoto;
    }

    public void setHyPhoto(byte[] hyPhoto) {
        this.hyPhoto = hyPhoto;
    }
}