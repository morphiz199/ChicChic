package com.example.projectchicchic.Model;

public class model {
    String ImageUrl,NameStore,PriceNail,Type,Branch,Time;

    model(){

    }

    public model(String imageUrl, String nameStore, String priceNail,String time , String branch,String type) {
        ImageUrl = imageUrl;
        NameStore = nameStore;
        PriceNail = priceNail;
        Type = type;
        Time = time;
        Branch = branch;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        this.Branch = branch;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getNameStore() {
        return NameStore;
    }

    public void setNameStore(String nameStore) {
        NameStore = nameStore;
    }
    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPriceNail() {
        return PriceNail;
    }

    public void setPriceNail(String priceNail) {
        PriceNail = priceNail;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
