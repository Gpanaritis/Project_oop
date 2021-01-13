package com.company;

public class Buyer extends User
{
    private int bonus=0;
    private String buyerCategory = "bronze";
    private ShoppingCart cart=new ShoppingCart();
    public Buyer(String Name , String Email)
    {
       super(Name,Email);
    }  
    
    //get
    public int getBonus(){return bonus;}
    public String getBuyerCategory(){return buyerCategory;}
    //Award Bonus
    public void awardBonus(int bonus){
        this.bonus += bonus;
        this.setbuyerCategory();
    }
    public void setbuyerCategory(){
        if (bonus>200)
            this.buyerCategory="gold";
        else if(bonus>100)
            this.buyerCategory="silver";
    }
    public void setBuyerCategory(String s){this.buyerCategory=s;}
    //toString method
    public String toString(){
        return "Name: " + getName() + "  Email: " + getEmail() + "    Bonus: " + bonus + "    Category: " + buyerCategory;
    }    
    //cart methods
    public void placeOrder(Item item,int quantity) throws NotEnoughStock,AlreadyInListException,NegativeException{
        cart.addItemOrdered(item,quantity);
    }
    public void removeItem(int id){
        cart.removeItemOrdered(id);
    }
    public void changeQuantity(int id, int q) throws NotEnoughStock,NegativeException{
        cart.changeItemOrderedQuantity(id,q);
    }    
    public void showCart(){
        cart.showCart(this);
    }    
    public void clearCart(){
        cart.clearCart();
    }    
    public void checkout(){
        cart.checkout(this);
    }    
    public double calculateNet(){
        return cart.calculateNet();
    }
    //eshop getters
    public double getCourrier(){
        return cart.calculateCourierCost(this);
    }    
    public int getOrderSize(){
        return cart.getOrderSize();
    }    
    public int getItemId(int i){
        return ((cart.getItem(i)).getItem()).getId();
    }
    public ItemOrdered getItemOrderedById(int id){
        return cart.getItem( cart.getIndexById(id) );
    }
}
