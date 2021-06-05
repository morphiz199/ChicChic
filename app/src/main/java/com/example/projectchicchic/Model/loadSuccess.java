package com.example.projectchicchic.Model;

public class loadSuccess {
    String User,ImageUrl,NameTtpe,Date,Time,Price,Branch;


    loadSuccess() {

    }

    public loadSuccess(String nameTtpe, String date, String time, String price, String branch,String user,String imageUrl) {

        ImageUrl = imageUrl;
        NameTtpe = nameTtpe;
        Date = date;
        Time = time;
        Price = price;
        Branch = branch;
        User = user;

    }
    public String getNameTtpe() {
        return NameTtpe;
    }

    public void setNameTtpe(String nameTtpe) {
        NameTtpe = nameTtpe;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        this.Branch = branch;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

}
