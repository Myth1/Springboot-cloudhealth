package com.shgbit.cloudhealth.exception;

public class ImageFile {
    private String imageAddress;

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public ImageFile(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
