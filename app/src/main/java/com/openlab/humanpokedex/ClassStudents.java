package com.openlab.humanpokedex;

import android.graphics.Bitmap;

public class ClassStudents {

    private String regNo, name, className, photosURL;

    public ClassStudents(String regNo, String name, String className, String photosURL) {
        this.regNo = regNo;
        this.name = name;
        this.className = className;
        this.photosURL = photosURL;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getStudentName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getPhotosURL() {
        return photosURL;
    }
}
