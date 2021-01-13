package com.company;

class Paper extends Item{
    private int weight;
    private int pages;
    public Paper(String name, double price, String description, int stock, int id, int weight, int pages){
        super(name,price,description,stock,id);
        this.weight=weight;
        this.pages=pages;
    }
    
    //String abstract methods
    public String getDetails(){
        return "Weight: " + weight + "   Pages: " + pages;
    }
    public String getClassName(){
        return "Paper";
    }
    //get
    public int getWeight(){return weight;}
    public int getPages(){return pages;}
    //set
    public void setWeight(int weight){this.weight=weight;}
    public void setPages(int pages){this.pages=pages;}
}