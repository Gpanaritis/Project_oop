package com.company;

public class Pencil extends Item{
    private double tipSize;
    private String type;
    public Pencil(String name, double price, String description, int stock, int id,double tipSize,String type){
        super(name,price,description,stock,id);
        this.tipSize=tipSize;
        this.type=type;
    }
    
    //String abstract methods
    public String getDetails(){
        return "TipSize: "+ tipSize + " Type:"+type;
        //return tipSize+" "+type;
    }
    public String getClassName(){
        return "Pencil";
    }
    //get
    public double getTipSize(){return tipSize;}
    public String getType(){return type;}
    //set
    public void setTipSize(double tipSize){this.tipSize=tipSize;}
    public void setType(String type){this.type=type;}
}