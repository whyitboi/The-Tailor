package com.example.junior.thetailorv20;

public class uploadImageAndComment {
    private String imgName, imgUrl, comments;

    public uploadImageAndComment(){

    }

    public uploadImageAndComment(String imgName, String imgUrl, String comments){

        if(imgName.trim().equals("")){
            imgName = "No Name";
        }
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        if(comments.trim().equals("")){
            comments = "No comment";
        }

        this.comments = comments;
    }
    public String getComments(){
        return comments;
    }

    public void setComments(String com){
        comments = com;
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
