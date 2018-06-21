package com.example.junior.thetailorv20;

public class uploadImage {
    private String imgName, imgUrl;

    public uploadImage(){

    }

    public uploadImage(String imgName, String imgUrl){

        if(imgName.trim().equals("")){
            imgName = "No Name";
        }
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
    public String getImgName(){
        return imgName;
    }
    public void setImgName(String name){
        imgName = name;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String url){
        imgUrl = url;
    }
}
