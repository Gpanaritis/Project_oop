package com.company;

abstract class Item
{ 
    private String Iname;
    private double Iprice;
    private String Idescription;
    private int Istock,id;
    public Item(String Iname,double Iprice,String Idescription,int Istock , int id){
        this.Iname=Iname;
        this.Iprice=Iprice;
        this.Idescription=Idescription;
        this.Istock=Istock;
        this.id=id;
    }
    //getters
    public String getName(){return Iname;}
    public double getPrice(){return Iprice;}
    public String getDescription(){return Idescription;}
    public int getStock(){return Istock;}
    public int getId(){return id;}
    //setters
    public void setName(String s){this.Iname=s;}
    public void setPrice(double p){this.Iprice=p;}
    public void setDescription(String f){this.Idescription=f;}
    public void setStock(int k){this.Istock=k;    }
    public void setId(int o){this.id=o;}
    //abstract
    abstract String getDetails();
    abstract String getClassName(); //επιστρέφει το όνομα της κλάσης του αντικειμένου
    //String methods
    public String getBasicInfo(){
        return "Name: " + Iname + "  Price: " + Iprice + "    Description: " + Idescription + "    Stock: " + Istock + "    Id:" + id;
        //return Iname+"\n"+Iprice+"\n"+Idescription+"\n"+Istock+"\n"+id;
    }
    public String toString(){
        return getClassName() + "   " + getBasicInfo() + "  " + getDetails();
        //return getBasicInfo() + "\n" + getDetails();
    }
}
