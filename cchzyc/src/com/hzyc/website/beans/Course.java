package com.hzyc.website.beans;

import java.util.Arrays;

public class Course {
    private Integer id;

    private String courseName;

    private String title;

    private String application;

    private String describe;

    private byte[] icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", title=" + title + ", application=" + application
				+ ", describe=" + describe + ", icon=" + Arrays.toString(icon) + "]";
	}
}