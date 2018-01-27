package com.hzyc.hzycpos.domain;

import java.util.List;

public class Dict {
    private Integer id;

    private String dictType;

    private String dictCode;

    private String dictName;

    private Byte sort;

    private Integer enabled;
    
   //字典名称 例如性别(这是dict_depict的字段[字典类描述]) 1-1
    private String typeName;
    //用来一对多   sex:男 女  从表就是自己
    private List<Dict> dList ;
    
    public List<Dict> getdList() {
		return dList;
	}

	public void setdList(List<Dict> dList) {
		this.dList = dList;
	}

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}