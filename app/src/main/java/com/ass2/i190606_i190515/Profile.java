package com.ass2.i190606_i190515;

public class Profile {



    public String name,email,pass,gender;

    public Profile (String name, String email, String pass,String gender)
    {
        this.name=name;
        this.email=email;
        this.gender=gender;
    }


    public Profile(){}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}

