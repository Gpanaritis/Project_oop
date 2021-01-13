package com.company;

class ItemOrdered{
    private Item item;
    private int quantity;
    public ItemOrdered(Item item,int quantity){
        this.item=item;
        this.quantity=quantity;
    }
    //get
    public Item getItem(){return item;}
    public int getQuantity(){return quantity;}
    //set
    public void setQuantity(int quantity){this.quantity=quantity;}
    //toString method
    public String toString(){
        return item + "    Quantity " + quantity + "    Price " + item.getPrice() * quantity;
    }
}