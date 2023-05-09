package com.eshop.entities;

import java.util.Date;
import com.eshop.utils.*;

public class users {
     private int id;
       private String email,password;

    /*public users(int id, String email, String password) {
        this.id = id;
        
        this.email = email;
        this.password = password;
        
    }*/

    public users(){}

    public users(int id, String email, String password) {
        this.id=id;
        this.email = email;
        this.password = password;
    }
     public users(String email, String password) {
     
        this.email = email;
        this.password = password;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + " email=" + email + ", password=" + password +  '}';
    }

    
    
    
    




    

   
    
    
    
}
