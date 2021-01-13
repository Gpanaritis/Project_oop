package com.company;

public class Pen extends Item
{
    private double tipSize;
    private String color;
    public Pen(String Iname,double Iprice,String Idescription,int Istock , int id ,String color, double tipSize){
    super(Iname,Iprice,Idescription,Istock,id);
    this.tipSize=tipSize;
    this.color=color;
    }
    
    //String abstract methods
    public String getDetails(){
        return "Color: " + color+"  TipSize: "+tipSize;
        //return tipSize +"\n" + color;
    }
    public String getClassName(){
        return "Pen";
    }
    //get
    public String getColor(){return color;}
    public double getTipSize(){return tipSize;}
    //set
    public void setColor(String color){this.color=color;}
    public void setTipSize(double tipSize){this.tipSize=tipSize;}
}
