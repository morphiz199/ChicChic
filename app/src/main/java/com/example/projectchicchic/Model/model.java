package com.example.projectchicchic.Model;

public class model {
    String ImageUrl,NameStore,PriceNail,TypeNail,Branch;

    model(){

    }

    public model(String imageUrl, String nameStore, String priceNail, String typeNail, String branch) {
        ImageUrl = imageUrl;
        NameStore = nameStore;
        PriceNail = priceNail;
        TypeNail = typeNail;
        this.Branch = branch;
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

    public String getPriceNail() {
        return PriceNail;
    }

    public void setPriceNail(String priceNail) {
        PriceNail = priceNail;
    }

    public String getTypeNail() {
        return TypeNail;
    }

    public void setTypeNail(String typeNail) {
        TypeNail = typeNail;
    }
}
