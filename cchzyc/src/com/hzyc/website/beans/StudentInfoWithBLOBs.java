package com.hzyc.website.beans;

public class StudentInfoWithBLOBs extends StudentInfo {
    private byte[] img;

    private byte[] workImg;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getWorkImg() {
        return workImg;
    }

    public void setWorkImg(byte[] workImg) {
        this.workImg = workImg;
    }
}