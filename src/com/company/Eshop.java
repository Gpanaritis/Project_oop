package com.company;

import java.util.ArrayList;
class Eshop
{
    private String name;
    private Owner owner;
    private ArrayList<Item> itemsList=new ArrayList<Item>();//Contains all items of eshop
    private ArrayList<Buyer> buyersList=new ArrayList<Buyer>();//contains all buyers
    public Eshop(String name , Owner owner){
        this.name=name;
        this.owner=owner;
    }
    
    //Item List methods
    public void addItem(Item item) throws AlreadyInListException,IdAlreadyInList{
        if(itemsList.contains(item) ){
            throw new AlreadyInListException("Item is already in list. Cannot be added.");
        }
        for(int i=0;i<itemsList.size();i++){
            if(item.getId() == (itemsList.get(i)).getId() ){
                throw new IdAlreadyInList("Id already exists");
            }
        }
        itemsList.add(item);
    }    
    public int getIndexById(int id){//Επιστρέφει την θέση του Item στο list με το συγκεκριμένο id
        int i=0;
        int k=(itemsList.get(i)).getId();
        while(k!=id){
            i++;
            k=(itemsList.get(i)).getId();
        }
        return i;
    }    
    public Item getItemById(int id){ //Επιστρέφει item με το id
        int i = getIndexById(id);
        return itemsList.get(i);
    }    
    public void removeItem(int id){// Αφαιρεί αντικείμενο με το id
        int i = getIndexById(id);
        itemsList.remove(i);
    }
    public void updateItemStock(int id, int i) throws NegativeException{
        if(i<0){
            throw new NegativeException("Negative stock");
        }
        getItemById(id).setStock(i);
    }
    //Buyer list methods
    public void addBuyer(Buyer buyer) throws AlreadyInListException,IdAlreadyInList{
        if(buyersList.contains(buyer)){
            throw new AlreadyInListException("Buyer is already registered . Cannot be added.");
        }
        for(int i=0;i<buyersList.size();i++){
            if(buyer.getEmail() == (buyersList.get(i)).getEmail() ){
                throw new IdAlreadyInList("Id already exists");
            }
        }
        buyersList.add(buyer);
    }    
    public int getIndexByEmail(String email){//Επιστρέφει την θέση του Buyer στο list με το συγκεκριμένο email
        int i=0;
        String k=(buyersList.get(i)).getEmail();
        while(!(k.equals(email))){
            i++;
            k=(buyersList.get(i)).getEmail();
        }
        return i;
    }    
    public void removeBuyer(String email){//Αφαιρεί τον χρήστη με το email
        buyersList.remove(getIndexByEmail(email));
        System.out.println("Buyer with Email " + email + " is removed from the list");
    }    
    //Show methods of eshop
    public ArrayList<String> showCategories(){
        int j=0; //Με ποιό αριθμό θα εμφανίζονται τα αντικείμενα
        int[] Sum = {0, 0, 0, 0}; //Πόσα αντικείμενα 0=Pen 1=Pencil 2=Notebook 3=Paper
        ArrayList<String> items = new ArrayList<String>();
        for(int i=0;i<itemsList.size();i++){
            if(itemsList.get(i) instanceof Pen){
                Sum[0]++;
            }
            if(itemsList.get(i) instanceof Pencil){
                Sum[1]++;
            }
            if(itemsList.get(i) instanceof Notebook){
                Sum[2]++;
            }
            if(itemsList.get(i) instanceof Paper){
                Sum[3]++;
            }
        }
        if(Sum[0] != 0){
            System.out.println((j+1) + ": Pen (" + Sum[0] + ")" );
            items.add("Pen");
            j++;
        }
        if(Sum[1] != 0){
            System.out.println((j+1) + ": Pencil (" + Sum[1] + ")" );
            items.add("Pencil");
            j++;
        }
        if(Sum[2] !=0){
            System.out.println((j+1) + ": Notebook (" + Sum[2] + ")" );
            items.add("Notebook");
            j++;
        }
        if(Sum[3] != 0){
            System.out.println((j+1) + ": Paper (" + Sum[3] + ")" );
            items.add("Paper");
            j++;
        }
        return items;
    }
    public ArrayList<Item> showProductsInCategory(String s){
        int j=0;
        ArrayList<Item> items = new ArrayList<Item>();
        for(int i=0;i<itemsList.size();i++){
            if(s.equals(itemsList.get(i).getClassName())){
                j++;
                System.out.println( j+ ". " + (itemsList.get(i)).getName());
                items.add( itemsList.get(i) );
            }
        }
        return items;
    }
    public void showProduct(int id){
        System.out.println(getItemById(id));
    }
    public void checkStatus(){
        for(int i=0;i<buyersList.size();i++){
            Buyer b;
            b=buyersList.get(i);
            System.out.println( (i+1) + ". " + b);
        }
    }
    //get methods
    public String getName(){return name;}
    public Owner getOwner(){return owner;}
    public Item getItem(int i){return itemsList.get(i);}
    public Buyer getBuyer(int i){return buyersList.get(i);}
    public int getBuyersListSize(){return buyersList.size();}
    //Help methods
    public boolean checkEmail(String email){
        boolean flag = false;
        for(int i=0;i<buyersList.size();i++){
            if ( email.equals( (buyersList.get(i)).getEmail() ) ){
                flag=true;
            }
        }
        return flag;
    }
}

