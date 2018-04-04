package com.hzyc.website.beans;

public class EmploymentNews {
    private Integer id;

    private String lifePhotoName;

    private String companyLogoName;

    private String stuName;

    private String position;

    //这是 用来分页绑定的属性
    private String nowPage;
    private String pageSize;
    private String maxPage;
    private String startPage;
    
    
    public String getStartPage() {
		return startPage;
	}

	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}

	public String getNowPage() {
		return nowPage;
	}

	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(String maxPage) {
		this.maxPage = maxPage;
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLifePhotoName() {
        return lifePhotoName;
    }

    public void setLifePhotoName(String lifePhotoName) {
        this.lifePhotoName = lifePhotoName == null ? null : lifePhotoName.trim();
    }

    public String getCompanyLogoName() {
        return companyLogoName;
    }

    public void setCompanyLogoName(String companyLogoName) {
        this.companyLogoName = companyLogoName == null ? null : companyLogoName.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
}