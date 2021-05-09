package com.example.projectchicchic;

public class Nail {
    String ImageNail;
    String TypeNail;
    String NameStore;
    String PriceNail;
    String TimeNail;

    public Nail(){
    }

    public Nail(String ImageNail, String TypeNail,String NameStore,String PriceNail,String TimeNail){
        this.ImageNail = ImageNail;
        this.TypeNail = TypeNail;
        this.NameStore = NameStore;
        this.PriceNail = PriceNail;
        this.TimeNail = TimeNail;
    }

    public String getImageNail(){

        return ImageNail;
    }
    public void setImageNail(String ImageNail){
        this.ImageNail = ImageNail;
    }

    public String getTypeNail(){
        return TypeNail;
    }
    public void setTypeNail(String TypeNail){
        this.TypeNail = TypeNail;
    }

    public String getNameStore(){
        return NameStore;

    }
    public void setNameStore(String NameStore){
        this.NameStore = NameStore;

    }

    public String getPriceNail(){
        return PriceNail;

    }
    public void setPriceNail(String PriceNail){
        this.PriceNail = PriceNail;

    }

    public String getTimeNail(){
        return TimeNail;

    }
    public void setTimeNail(String TimeNail){
        this.TimeNail = TimeNail;

    }


}
