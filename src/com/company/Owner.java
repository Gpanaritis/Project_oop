package com.company;

public class Owner extends User
{
    private boolean isAdmin=true;
    public Owner(String Name , String Email){
        super(Name,Email);
    }
    //get,set
    public boolean getAdmin(){return isAdmin;}
    public void setAdmin(boolean isAdmin){this.isAdmin=isAdmin;}
    //toString
    public String toString(){
        return "Name: " + getName() + "  Email: " + getEmail() + " isAdmin: " + isAdmin;
    }
}
