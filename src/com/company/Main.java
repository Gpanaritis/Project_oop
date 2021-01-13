package com.company;

public class Main{
    public static void main(String args[]){
        //Creating objects
        Pen[] pen = {new Pen("Crayolla",5, "It is a pen", 15, 112, "Blue",0.7),
           new Pen("Crayolle",10, "It is a pen", 150, 113, "Black",0.5),
           new Pen("Pilot",5, "It is a pen", 22, 114, "Red",1.0),
           new Pen("Jetstream",15, "It is a pen", 22, 115, "Red",1.0)
        };
        Notebook[] note = {new Notebook("Skag45", 5.3, "It is a notebook", 30, 542,4),
           new Notebook("Bullet", 5.9, "It is a notebook", 31, 543,3),
           new Notebook("Tesoro", 4, "It is a notebook", 32, 544,4),
        };       
        Pencil[] pencil= {new Pencil("Graf",5,"It is a pencil",540,10,1.0,"2B"),
            new Pencil("Apple",750,"It is a supreme pencil",12,11,0.5,"4C"),
            new Pencil("Faber",3,"It is a pencil",565,12,0.7,"2B")
        };
        Paper[] paper= {new Paper("Zewa", 7.5, "Exclusive extra soft", 420, 95, 23,8),
           new Paper("Glaros", 3, "White rolls", 48, 96, 19,8)
        };
        //Creaating the rest
        Owner owner = new Owner("Lvl_100_Boss","example");        
        Buyer[] buyer = {new Buyer("Kostantinos","up1072533"),
           new Buyer("Nektarios","up1072594"),
           new Buyer("Giannis","up1072632")
        };
        Eshop notEA = new Eshop("Pls_give_money",owner);
        Menu menu = new Menu(notEA);
        //adding items to eshop
        try{
            for(int i=0;i<pen.length;i++){
                notEA.addItem(pen[i]);
            }
            for(int i=0;i<note.length;i++){
                notEA.addItem(note[i]);
            }
            for(int i=0;i<pencil.length;i++){
                notEA.addItem(pencil[i]);
            }
            for(int i=0;i<paper.length;i++){
                notEA.addItem(paper[i]);
            }
            for(int i=0;i<buyer.length;i++){
                notEA.addBuyer(buyer[i]);
            }
            //notEA.addItem(pen[0]); //to test exception
        }
        catch(Exception e){ e.printStackTrace(System.out);}
        //start menu
        menu.start();
    }
}