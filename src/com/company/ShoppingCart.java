package com.company;

import java.util.ArrayList;
import java.util.Scanner;
public class ShoppingCart
{
    private ArrayList<ItemOrdered> orderList= new ArrayList<ItemOrdered>();
    //private int starterQuantity;
    //public void  setQ(int s){this.starterQuantity=s;}
    //public int getQ(){return starterQuantity;}
    
    // Order commands 
    public void addItemOrdered(Item item, int quantity) throws NotEnoughStock,AlreadyInListException,NegativeException{
        for(int i=0;i<orderList.size();i++){//Ηδη υπάρχει αντικείμενο
            if (item.equals((orderList.get(i)).getItem())){
                throw new AlreadyInListException("You have already added this item");
            }
        }
        if(quantity>item.getStock()){//δεν έχω αρκετο stock
            throw new NotEnoughStock("There aren't enough items");
        }
        if(quantity <= 0){
            throw new NegativeException("We cannot give negative quantity");
        }
        orderList.add(new ItemOrdered(item,quantity));
        item.setStock(item.getStock()-quantity);
        //setQ(quantity);
    }
    public int getIndexById(int id){//Επιστρέφει την θέση του ItemOrdered στο list
        int i=0;
        int k=((orderList.get(i)).getItem()).getId();
        while(k!=id){
            i++;
            k=((orderList.get(i)).getItem()).getId();
        }
        return i;
    }
    public void removeItemOrdered(int id){
        int i = getIndexById(id);
        Item item = (orderList.get(i)).getItem();
        item.setStock(item.getStock()+(orderList.get(i)).getQuantity());
        orderList.remove(i);        
    }
    public void changeItemOrderedQuantity(int id, int q) throws NotEnoughStock,NegativeException{
        int i = getIndexById(id);
        ItemOrdered k = orderList.get(i);
        i = q - k.getQuantity(); // Το πλήθος των αντικειμένων που παίρνουμε
        if( i > (k.getItem()).getStock() ){
            throw new NotEnoughStock("There aren't enough items");
        }
        if(q<=0){
            throw new NegativeException("We cannot give negative quantity");
        }
        k.setQuantity(q);
        (k.getItem()).setStock( (k.getItem()).getStock() - i ); //Τροποποιεί το stock
    }
    //cart commands
    public void showCart(Buyer buyer){
        for (int i=0; i<orderList.size();i++){
            System.out.println( "Item " + (i+1) + ": " + orderList.get(i));            
        }
        if(orderList.size() == 0){
            System.out.println("Your cart is empty");
        }
        System.out.println();
        System.out.println( "Item cost: " + calculateNet() + "    Transport fee: " + calculateCourierCost(buyer) );
        System.out.println( "Total: " + (calculateNet() + calculateCourierCost(buyer)) );
    }
    public void clearCart(){
        for(int i=0;i<orderList.size();i++){
            int id = ((orderList.get(i)).getItem()).getId();
            removeItemOrdered(id);
        }
    }
    public void checkout(Buyer buyer){
        Scanner myScan = new Scanner(System.in);
        showCart(buyer);
        System.out.println();
        System.out.println("Do you want to complete transaction y/n ?");
        String answer = myScan.nextLine();
        if( answer.equals("y") ){
            buyer.awardBonus( (int) calculateNet() / 10 );
            orderList.clear();
        }
    }
    //calculate commands
    public double calculateNet(){
        double s=0;
        ItemOrdered k;
        for (int i=0; i<orderList.size();i++){
            k = orderList.get(i);
            s += (k.getItem()).getPrice() * k.getQuantity();
        }
        return s;
    }
    public double calculateCourierCost(Buyer buyer){
        double s = calculateNet() * 2 / 100;
        if ( (buyer.getBuyerCategory()).equals("gold") ){
            s=0;
        }
        else if( (buyer.getBuyerCategory()).equals("silver") ){
            s = s/2;
        }
        else if( s < 3 ){
            s = 3;
        }
        return s;
    }
    public ItemOrdered getItem(int i){return orderList.get(i);}
    public int getOrderSize(){ return orderList.size();}
}
