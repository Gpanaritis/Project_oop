package com.company;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
class Menu{
    private Eshop eshop;
    private Buyer buyer;
    private Owner owner;
    public Menu(Eshop eshop){
        this.eshop=eshop;
        owner = eshop.getOwner();
    }
    
    public void start(){//Την καλώ για αρχή του προγράμματος. Με πάει στο loading screen ή τελειώνει το πρόγραμμα
        System.out.println("Do you want to login or exit ?  \n(For exit type 'exit'    type anything else to proceed) ");
        if( check("exit") ){
            cls();
        }
        else{
            cls();
            LoginScreen();
        }
    }
    private void LoginScreen(){//login
        System.out.println("Welcome User, please enter your email");
        String answer = readLine();
        if( answer.equals( (eshop.getOwner()).getEmail() ) ){//is Owner
            cls();
            welcomeOwner();
        }
        else if (! eshop.checkEmail(answer) ){ // Is not in List
            cls();
            //Does the user want new account?
            System.out.println("We could not find your email in our list \n Do you want to create a new account ?  y/n");
            if( check("y") ){ // Wants to create account
                cls();    
                System.out.println("Welcome User, please enter your Name");
                String answer2 = readLine();
                try{
                    eshop.addBuyer( new Buyer(answer2,answer) );
                }
                catch (AlreadyInListException e) {System.out.println(e);}
                catch (IdAlreadyInList e) {System.out.println(e);}
                cls();
                this.buyer = eshop.getBuyer( (eshop.getIndexByEmail(answer)) );
                welcomeBuyer();
            }
            else{ // Does not want to create account
                cls();
                start();
            }
        }
        else { // Buyer and is in list
            cls();
            this.buyer = eshop.getBuyer( (eshop.getIndexByEmail(answer)) );
            welcomeBuyer();
        }
    }
    private void welcomeBuyer(){
        System.out.println(buyer + "\n");
        System.out.println("What do YOU want to do    (press  number) \n");
        System.out.println("1. Browse Store \n2. View Cart \n3. Checkout \n4. Back \n5. Logout \n6. Exit \n7. Ask me again");
        try{
            int answer = readInt();
            cls();
            if( answer == 1 ){//Browse Store
                BrowseStore();
            }
            else if( answer == 2 ){//View Cart
                ViewCart();
            }
            else if( answer == 3 ){//Checkout, then return to welcome menu
                buyer.checkout();
                cls();
                welcomeBuyer();
            }
            else if( answer == 4){//Back one step
                start();
            }
            else if( answer == 5){//Logout
                start();
            }
            else if( answer==6){}//Exit (Do nothing)
            else{// If not valid answer ask again
                welcomeBuyer();
            }
        }
        catch(InputMismatchException e){cls(); welcomeBuyer();}//If not valid input
    }
    //Browse Store
    private void BrowseStore(){
        System.out.println(eshop.getName() + "\n" );
        ArrayList<String> items = eshop.showCategories();
        System.out.println((items.size()+1) + ": Back");
        System.out.println((items.size()+2) + ": Logout");
        System.out.println((items.size()+3) + ": Exit\n");
        System.out.println("What item category do YOU want to choose? ");
        try{
            int answer = readInt();
            cls();
            if( answer>0 && answer <= items.size() ){
                ChooseItem( items.get(answer-1) );
            }
            else if(answer == (items.size()+1) ){
                welcomeBuyer();
            }
            else if(answer == (items.size()+2) ){
                start();
            }
            else if(answer == (items.size()+3) ){}
            else{
                BrowseStore();
            }
        }
        catch(InputMismatchException e){cls(); BrowseStore();}//If not valid input
    }
    private void ChooseItem( String category ){        
        ArrayList<Item> items = eshop.showProductsInCategory( category );
        System.out.println((items.size()+1) + ": Back");
        System.out.println((items.size()+2) + ": Logout");
        System.out.println((items.size()+3) + ": Exit\n");
        System.out.println("What item do YOU want to order? ");
        try{
            int answer = readInt();
            cls();
            if( answer>0 && answer <= items.size() ){
                placeOrder(items.get(answer-1));
            }
            else if(answer == (items.size()+1) ){
                BrowseStore();
            }
            else if(answer == (items.size()+2) ){
                start();
            }
            else if(answer == (items.size()+3) ){}
            else{
                ChooseItem(category);
            }
        }
        catch(InputMismatchException e){cls(); ChooseItem(category);}//If not valid input
    }
    private void placeOrder(Item item){
        System.out.println(item + "\n");
        System.out.println("Do you want to add item to your cart?  y/n");
        if( check("y") ){
            System.out.println( "How many do you want to add?" );
            try{
                int answer = readInt();
                buyer.placeOrder(item,answer);
                System.out.println("Item added succesfuly\nYou can view your cart  complete purchase from the welcome menu");
                waitInput();
                cls();
                welcomeBuyer();
            }
            catch(InputMismatchException e){//If not valid input
                System.out.println("Please enter a number"); 
                waitInput(); cls(); placeOrder(item);
            }
            catch(NotEnoughStock e){//Not enough stock
                System.out.println("We do not have that many in stock. Please enter a smaller amount"); 
                waitInput(); cls(); placeOrder(item);
            }
            catch(AlreadyInListException e){//already in list
                System.out.println("You have already added this item. If you want to change amount or remove it, you can do it from View Cart menu"); 
                waitInput(); cls(); welcomeBuyer();
            }
            catch(NegativeException e){//negative
                System.out.println("Please enter a positive number");
                waitInput(); cls(); placeOrder(item);
            }
        }
        else{//Does not want to add item
            ChooseItem( item.getClassName() );            
        }
    }
    //View Cart
    private void ViewCart(){
        buyer.showCart();
        int j = buyer.getOrderSize();
        System.out.println((j+1) + ": Clear cart");
        System.out.println((j+2) + ": Checkout");
        System.out.println((j+3) + ": Back");
        System.out.println((j+4) + ": Logout");
        System.out.println((j+5) + ": Exit\n");
        try{
            int answer = readInt();
            cls();
            if(answer>0 && answer<=j){
                editCart(buyer.getItemId(j-1));
            }
            else if(answer == j+1){
                buyer.clearCart();
                System.out.println("\nThe cart is cleared");
                waitInput();
                cls();
                welcomeBuyer();
            }
            else if(answer == j+2){
                buyer.checkout();
                cls();
                welcomeBuyer();
            }
            else if(answer == j+3){
                welcomeBuyer();
            }
            else if(answer == j+4){
                start();
            }
            else if(answer == j+5){}
            else{
                ViewCart();
            }
        }
        catch(InputMismatchException e){cls(); ViewCart();}//If not valid input
    }
    private void editCart(int id){
        System.out.println(buyer.getItemOrderedById(id) + "\n");
        System.out.println("1. Delete order");
        System.out.println("2. Edit order quantity");
        System.out.println("3. Back");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        try{
            int answer = readInt();
            cls();
            if(answer == 1){
                buyer.removeItem(id);
                System.out.println("Item was removed from your cart");
                waitInput();
                cls();
                ViewCart();
            }
            else if(answer == 2){
                System.out.println("How many do you want?");
                answer = readInt();
                buyer.changeQuantity(id,answer);
                System.out.println("Item quantity was modified");
                waitInput();
                cls();
                ViewCart();
            }
            else if(answer == 3){
                ViewCart();
            }
            else if(answer == 4){
                start();
            }
            else if(answer == 5){}
            else{
                editCart(id);
            }
        }
        catch (InputMismatchException e){cls(); editCart(id);}
        catch (NotEnoughStock e){
            System.out.println("There is not enough stock");
            waitInput();
            cls();
            editCart(id);
        }
        catch(NegativeException e){//negative
                System.out.println("Please enter a positive number");
                waitInput(); cls(); editCart(id);
        }
    }
    //Owner methods
    private void welcomeOwner(){
        System.out.println(owner + "\n");
        System.out.println("What do YOU want to do    (press  number) \n");
        System.out.println("1. Browse Store \n2. Check Status \n3. Back \n4. Logout \n5. Exit \n6. Ask me again");
        try{
            int answer = readInt();
            cls();
            if( answer == 1 ){//Browse Store
                BrowseOwnerStore();
            }
            else if( answer == 2 ){//Check status
                CheckStatus();
            }
            else if( answer == 3){//Back one step
                start();
            }
            else if( answer == 4){//Logout
                start();
            }
            else if( answer==5){}//Exit (Do nothing)
            else{// If not valid answer ask again
                welcomeOwner();
            }
        }
        catch(InputMismatchException e){cls(); welcomeOwner();}//If not valid input
    }
    private void BrowseOwnerStore(){
        System.out.println(eshop.getName() + "\n" );
        ArrayList<String> items = eshop.showCategories();
        System.out.println((items.size()+1) + ": Back");
        System.out.println((items.size()+2) + ": Logout");
        System.out.println((items.size()+3) + ": Exit\n");
        System.out.println("What item category do YOU want to choose? ");
        try{
            int answer = readInt();
            cls();
            if( answer>0 && answer <= items.size() ){
                ChooseOwnerItem( items.get(answer-1) );
            }
            else if(answer == (items.size()+1) ){
                welcomeOwner();
            }
            else if(answer == (items.size()+2) ){
                start();
            }
            else if(answer == (items.size()+3) ){}
            else{
                BrowseOwnerStore();
            }
        }
        catch(InputMismatchException e){cls(); BrowseOwnerStore();}//If not valid input
    }
    private void ChooseOwnerItem( String category ){        
        ArrayList<Item> items = eshop.showProductsInCategory( category );
        System.out.println((items.size()+1) + ": Back");
        System.out.println((items.size()+2) + ": Logout");
        System.out.println((items.size()+3) + ": Exit\n");
        System.out.println("What item do YOU want to edit? ");
        try{
            int answer = readInt();
            cls();
            if( answer>0 && answer <= items.size() ){
                EditItem(items.get(answer-1));
            }
            else if(answer == (items.size()+1) ){
                BrowseOwnerStore();
            }
            else if(answer == (items.size()+2) ){
                start();
            }
            else if(answer == (items.size()+3) ){}
            else{
                ChooseOwnerItem(category);
            }
        }
        catch(InputMismatchException e){cls(); ChooseOwnerItem(category);}//If not valid input
    }
    private void EditItem(Item item){
        System.out.println(item + "\n");
        System.out.println("Do you want to edit item ?  y/n");
        if( check("y") ){
            System.out.println( "How much stock do you want available?" );
            try{
                int answer = readInt();
                eshop.updateItemStock(item.getId(),answer);
                System.out.println("Item edited succesfuly");
                waitInput();
                cls();
                welcomeOwner();
            }
            catch(InputMismatchException e){//If not valid input
                System.out.println("Please enter a number"); 
                waitInput(); cls(); EditItem(item);
            }
            catch(NegativeException e){//negative
                System.out.println("Please enter a positive number");
                waitInput(); cls(); EditItem(item);
            }
        }
        else{//Does not want to add item
            ChooseOwnerItem( item.getClassName() );            
        }
    }
    private void CheckStatus(){
        eshop.checkStatus();
        int j = eshop.getBuyersListSize();
        System.out.println( (j+1) + ". Back");
        System.out.println( (j+2) + ". Logout");
        System.out.println( (j+3) + ". Exit");
        try{
            int answer = readInt();
            cls();
            if(answer>0 && answer <=j){
                seeBuyer(eshop.getBuyer(answer-1) ); 
            }
            else if(answer == (j+1) ){
                welcomeOwner();
            }
            else if(answer == (j+2) ){
                start();
            }
            else if(answer == (j+3)){}
            else{
                CheckStatus();
            }
        }
        catch(InputMismatchException e){cls(); CheckStatus();}//If not valid input
    }    
    private void seeBuyer(Buyer buyer1){
        buyer1.showCart();
        System.out.println("\nDo you want to delete this user?  y/n");
        if(check("y")){
            eshop.removeBuyer(buyer.getEmail());
            waitInput();        
        }
        cls();
        CheckStatus();
    }
    // methods that help
    private void cls(){// Clears screen
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    private boolean check( String str ){ // Checks if user input equals given string
        Scanner MyScan1 = new Scanner(System.in); //creating new scanner, do not use previous scanner, problem in placeOrder()
        String answer = MyScan1.nextLine();
        System.out.println(answer);
        if ( answer.equals(str) ){
            return true;
        }
        else{
            return false;
        }
    }

    private void waitInput(){
        Scanner MyScan1 = new Scanner(System.in); //creating new scanner, do not use previous scanner, problem in placeOrder()
        System.out.println("Please press any button to continue");
        String answer = MyScan1.nextLine();
    }

    private int readInt(){
        Scanner MyScan1 = new Scanner(System.in); //creating new scanner, do not use previous scanner, problem in placeOrder()
        int num = MyScan1.nextInt();
        return num;
    }

    private String readLine(){
        Scanner MyScan1 = new Scanner(System.in); //creating new scanner, do not use previous scanner, problem in placeOrder()
        String str = MyScan1.nextLine();
        return str;
    }
}