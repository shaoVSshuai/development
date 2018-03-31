package com.hzyc.website.beans;

public class EmploymentNewsWithBLOBs extends EmploymentNews {
    private byte[] lifePhoto;

    private byte[] companyLogo;

    public byte[] getLifePhoto() {
        return lifePhoto;
    }

    public void setLifePhoto(byte[] lifePhoto) {
        this.lifePhoto = lifePhoto;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }
}