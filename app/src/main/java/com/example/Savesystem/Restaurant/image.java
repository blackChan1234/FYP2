package com.example.Savesystem.Restaurant;

public class image {
    private String imgPath;
    
    public image(String imgPath) {
        this.imgPath = imgPath;
    }
    public image(){
        imgPath="demo.png";
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
    public String getImgPath() {
        return imgPath;
    }
}
