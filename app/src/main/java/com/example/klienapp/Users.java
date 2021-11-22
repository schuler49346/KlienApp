package com.example.klienapp;

public class Users {

    public String Name;
    public String Email;

    public Users(){

    }

    public Users(String name, String email){
        this.Name = name;
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
