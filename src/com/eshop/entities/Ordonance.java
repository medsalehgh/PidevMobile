package com.eshop.entities;


public class Ordonance {
     private int id;
       private String idOrdonnance,nomMedicaments,nomPatient,SignatureMedecin;

    /*public Ordonance(int id, String email, String password) {
        this.id = id;
        
        this.email = email;
        this.password = password;
        
    }*/

    public Ordonance(){}

    public Ordonance(int id,String idOrdonnance, String nomMedicaments, String nomPatient, String SignatureMedecin) {
        this.id = id;
        this.idOrdonnance = idOrdonnance;
        this.nomPatient = nomPatient;
        this.nomPatient = nomPatient;
        this.SignatureMedecin = SignatureMedecin;

    }

    public Ordonance(String idOrdonnance, String nomMedicaments, String nomPatient, String SignatureMedecin) {
        this.idOrdonnance = idOrdonnance;
        this.nomPatient = nomPatient;
        this.nomMedicaments = nomMedicaments;
        this.SignatureMedecin = SignatureMedecin;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdOrdonnance() {
        return idOrdonnance;
    }

    public void setIdOrdonnance(String idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }

    public String getNomMedicaments() {
        return nomMedicaments;
    }

    public void setNomMedicaments(String nomMedicaments) {
        this.nomMedicaments = nomMedicaments;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getSignatureMedecin() {
        return SignatureMedecin;
    }

    public void setSignatureMedecin(String SignatureMedecin) {
        this.SignatureMedecin = SignatureMedecin;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idOrdonnance=" + idOrdonnance + ", nomMedicaments=" + nomMedicaments + ", nomPatient=" + nomPatient + ", SignatureMedecin=" + SignatureMedecin + '}';
    }

    
    

    

    
    
    
    




    

   
    
    
    
}
