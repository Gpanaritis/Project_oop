package com.company;

class Notebook extends Item{
    private int sections;
    public Notebook(String name, double price, String description, int stock, int id,int sections){
        super(name,price,description,stock,id);
        this.sections=sections;
    }
    
    //String abstract methods
    public String getDetails(){
        return "Sections: " + sections;
    }
    public String getClassName(){
        return "Notebook";
    }
    //get
    public int getSections(){return sections;}
    //set
    public void setSections(int sections){this.sections=sections;}
}