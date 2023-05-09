package com.eshop.gui.reclamation;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.eshop.entities.Ordonance;
import com.eshop.services.OrdonanceService;

public class EditO  extends Form {
    
    //Modif
      Form previous; 
    public EditO (Form previous, Ordonance r ) {
        
            super("Modifier transaction", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            TextField idOrdonnance = new TextField(r.getIdOrdonnance(), "idOrdonnance");
            TextField nomMedicaments = new TextField(r.getNomMedicaments(), "nomMedicaments");
            TextField nomPatient = new TextField(r.getNomPatient(), "nomPatient");
            TextField signatureMedecin = new TextField(r.getSignatureMedecin(), "signatureMedecin");
            
        
         Button btnModifier = new Button("Edit");
         Button btnAnnuler = new Button("Annuler");
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lidOrdonnance=new Label("idOrdonnance: "+idOrdonnance.getText());
                Label lnomMedicaments=new Label("nomMedicaments : "+nomMedicaments.getText());
                Label lnomPatient=new Label("nomMedicaments: "+nomMedicaments.getText());
                Label lsignatureMedecin=new Label("signatureMedecin: "+signatureMedecin.getText());

                
                f2.add(lidOrdonnance);
                f2.add(lnomMedicaments);
                f2.add(lnomPatient);
                f2.add(lsignatureMedecin);
                                        
                f2.show();
                      
              r.setIdOrdonnance(idOrdonnance.getText());
              r.setNomMedicaments(nomMedicaments.getText()); 
              r.setNomPatient(nomPatient.getText());
              r.setSignatureMedecin(signatureMedecin.getText()); 
              
              
              
     
             /* if(OrdonanceService.getInstance().modifier(r)) { 
                new ShowAllO(previous).show();
              } */
             
             
             try {
            if(OrdonanceService.getInstance().modifier(r)) { 
                new ShowAllO(previous).show();
            } else {
                Dialog.show("Error", "Unable to update transaction", new Command("OK"));
            }
        } catch(Exception e) {
            Dialog.show("Error", "Unable to update transaction: " + e.getMessage(), new Command("OK"));
        }     
             
            }
        });
        addAll(idOrdonnance,nomMedicaments,nomPatient,signatureMedecin,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAllO(previous).show();
         });
    }
  
}
