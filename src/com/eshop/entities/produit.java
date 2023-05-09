package com.eshop.entities;

import java.util.Date;
import com.eshop.utils.*;

public class produit {
    
    private int id;
     private String id_p,type_p,nom_p,prix_p,stock_p;
    
    public produit() {}

    public produit(int id,String id_p,String prix_p,String stock_p, String nom_p, String type_p) {
        this.id = id;
        this.id_p = id_p;
        this.nom_p = nom_p;
        this.type_p=type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
       
    }

    public produit(String id_p,String prix_p,String stock_p, String nom_p, String type_p) {   
        this.id_p = id_p;
        this.nom_p = nom_p;
        this.type_p=type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
    }

    public int getId() {
        return id;
    }

    public String getType_p() {
        return type_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getId_p() {
        return id_p;
    }

    public String getPrix_p() {
        return prix_p;
    }

    public String getStock_p() {
        return stock_p;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType_p(String type_p) {
        this.type_p = type_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setId_p(String id_p) {
        this.id_p = id_p;
    }

    public void setPrix_p(String prix_p) {
        this.prix_p = prix_p;
    }

    public void setStock_p(String stock_p) {
        this.stock_p = stock_p;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type_p=" + type_p + ", nom_p=" + nom_p + ", id_p=" + id_p + ", prix_p=" + prix_p + ", stock_p=" + stock_p + '}';
    }
    
    
    




    

   
    
    
    
}
