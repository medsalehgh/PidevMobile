package com.eshop.entities;

import java.util.Date;
import com.eshop.utils.*;

public class Reclamation {
     private int id;
       private String nom,prenom,numero;

 
    public Reclamation(){}

    public Reclamation(int id, String nom, String prenom,String numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
         this.numero = numero;
        

    }

    public Reclamation(String nom, String prenom,String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

   

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numero=" + numero  + '}';
    }

   
    
    
    
    




    

   
    
    
    
}
