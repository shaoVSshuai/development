package com.hzyc.website.beans;

import java.util.List;

public class Privilege {
    private Integer id;

    private String mName;

    private String mUrl;

    private Integer level;

    private Integer pId;

    private String imgUrl;
    
    //封装二级菜单
    private List<Privilege> p2List;
    

    public List<Privilege> getP2List() {
		return p2List;
	}

	public void setP2List(List<Privilege> p2List) {
		this.p2List = p2List;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl == null ? null : mUrl.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}