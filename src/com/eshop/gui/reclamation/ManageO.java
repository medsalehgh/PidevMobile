package com.eshop.gui.reclamation;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
import com.eshop.services.OrdonanceService;
import com.eshop.gui.reclamation.ShowAllO;




public class ManageO extends Form {
     Form previous; 
     //add  GUI
    public ManageO (Form previous) {        
            super("Ajouter transaction", new BoxLayout(BoxLayout.Y_AXIS));           
             super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
            TextField idOrdonnance = new TextField("", "idOrdonnance");
            TextField nomMedicaments = new TextField("", "nomMedicaments");
            TextField nomPatient = new TextField("", "nomPatient");
            TextField signatureMedecin = new TextField("", "signatureMedecin");

          
            
            
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lidOrdonnance=new Label("idOrdonnance: "+idOrdonnance.getText());
                Label lnomMedicaments=new Label("nomMedicaments: "+nomMedicaments.getText());
                Label lnomPatient=new Label("nomPatient: "+nomPatient.getText());
                Label lsignatureMedecin=new Label("signatureMedecin: "+signatureMedecin.getText());

                
                
                f2.add(lidOrdonnance);
                f2.add(lnomMedicaments);
                f2.add(lnomPatient);
                f2.add(lsignatureMedecin);

                              
                f2.show();
                if ((idOrdonnance.getText().length()==0)&&(nomMedicaments.getText().length()==0) &&(nomPatient.getText().length()==0)&&(signatureMedecin.getText().length()==0)       )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{ 
                             Ordonance t = new Ordonance(idOrdonnance.getText(),nomMedicaments.getText() ,nomPatient.getText(),signatureMedecin.getText()                  );
                            if( OrdonanceService.getInstance().addTask(t))
                            {
                               Dialog.show("Success","Transaction added",new Command("OK"));
                               new ShowAllO(previous).show();
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                return;


                 
            }
        });
        
        addAll(idOrdonnance, nomMedicaments,nomPatient,signatureMedecin, btnValider);
        
//      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
}