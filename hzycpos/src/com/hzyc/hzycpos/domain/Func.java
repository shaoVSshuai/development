package com.hzyc.hzycpos.domain;

import java.io.Serializable;

public class Func  implements Serializable{
    /**
	 * ���ڶ�������
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}