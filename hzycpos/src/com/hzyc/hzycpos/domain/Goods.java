package com.hzyc.hzycpos.domain;

import java.io.Serializable;

public class Goods implements Serializable{
    private Integer id;

    private String goodCode;

    private String goodName;

    private String goodKindId;

    private String goodPrice;

    private String goodRemark;
    
    private String fullPinyin;
    
    
	private String easyPinyin;

    private String p1;

    private String p2;

    private String p3;

    private String p4;

    private String p5;

    private byte[] goodPhoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getgoodKindId() {
        return goodKindId;
    }

    public void setgoodKindId(String goodKindId) {
        this.goodKindId = goodKindId == null ? null : goodKindId.trim();
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice == null ? null : goodPrice.trim();
    }

    public String getGoodRemark() {
        return goodRemark;
    }

    public void setGoodRemark(String goodRemark) {
        this.goodRemark = goodRemark == null ? null : goodRemark.trim();
    }
    
    public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getEasyPinyin() {
		return easyPinyin;
	}

	public void setEasyPinyin(String easyPinyin) {
		this.easyPinyin = easyPinyin;
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

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4 == null ? null : p4.trim();
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5 == null ? null : p5.trim();
    }

    public byte[] getGoodPhoto() {
        return goodPhoto;
    }

    public void setGoodPhoto(byte[] goodPhoto) {
        this.goodPhoto = goodPhoto;
    }
}