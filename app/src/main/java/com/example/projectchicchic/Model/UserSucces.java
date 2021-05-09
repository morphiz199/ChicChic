package com.example.projectchicchic.Model;

public class UserSucces {

    String ImageUrl,NameTtpe,Date,Time,Price,Branch;


    public UserSucces() {

    }

    public UserSucces(String nameTtpe, String date, String time, String price, String branch) {
        NameTtpe = nameTtpe;
        Date = date;
        Time = time;
        Price = price;
        Branch = branch;

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

    public String getNameTtpe() {
        return NameTtpe;
    }

    public void setNameTtpe(String nameTtpe) {
        NameTtpe = nameTtpe;
    }

}
