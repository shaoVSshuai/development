package com.hzyc.website.beans;

public class EmploymentNews {
    private Integer id;

    private String lifePhotoName;

    private String companyLogoName;

    private String stuName;

    private String position;

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