package com.hzyc.hzycpos.domain;

import java.io.Serializable;
import java.io.Serializable;
import java.util.List;

public class Privilege implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String mName;

    private String mUrl;

    private Integer level;

    private Integer pId;

    private String imgUrl;

    private String correspondingXml;

    private Integer funcId;

    private Integer sort;

    private Integer enabled;
    
    /**
     * 封装用户所拥有的权限信息
     * */
    private List<Privilege> pList;

    
    public List<Privilege> getpList() {
		return pList;
	}

	public void setpList(List<Privilege> pList) {
		this.pList = pList;
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

    public String getCorrespondingXml() {
        return correspondingXml;
    }

    public void setCorrespondingXml(String correspondingXml) {
        this.correspondingXml = correspondingXml == null ? null : correspondingXml.trim();
    }

    public Integer getDictId() {
        return funcId;
    }

    public void setDictId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}