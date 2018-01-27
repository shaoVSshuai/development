package com.hzyc.hzycpos.domain;

public class Config {
    private Integer id;

    private String key;

    private Integer valueDict;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getValueDict() {
        return valueDict;
    }

    public void setValueDict(Integer valueDict) {
        this.valueDict = valueDict;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}