package com.company;

abstract class User
{
    private String Name,Email;
    public User(String Name , String Email){
        this.Name=Name;
        this.Email=Email;
    }
    //get
    public String getName(){return Name;}
    public String getEmail(){return Email;}
    //set
    public void setName(String name){this.Name=name;}
    public void setEmail(String email){this.Email=email;}
}
