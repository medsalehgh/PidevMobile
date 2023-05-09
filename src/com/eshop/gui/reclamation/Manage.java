package com.eshop.gui.reclamation;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
import com.eshop.services.ReclamationService;
import com.eshop.gui.reclamation.ShowAll;




public class Manage extends Form {
     Form previous; 
     //add  GUI
    public Manage (Form previous) {        
            super("Ajouter transaction", new BoxLayout(BoxLayout.Y_AXIS));           
             super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
           
            TextField nom = new TextField("", "nom");
            TextField prenom = new TextField("", "prenom");
            
            TextField numero = new TextField("", "numero");

            
            
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lnom=new Label("nom: "+nom.getText());
                Label lprenom=new Label("prenom: "+prenom.getText());
                Label lnumero=new Label("numero: "+numero.getText());

                
                f2.add(lnom);
                f2.add(lprenom);
                f2.add(lnumero);
            
                f2.show();
                
                if ((nom.getText().length()==0)&&(prenom.getText().length()==0)&&(numero.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{ 
                             Reclamation t = new Reclamation(nom.getText(),prenom.getText(),numero.getText());
                            if( ReclamationService.getInstance().addTask(t))
                            {
                               Dialog.show("Success","Transaction added",new Command("OK"));
                               new ShowAll(previous).show();
                            }else
                                Dialog.show("Success", "Connection accepted", new Command("OK"));
                            new ShowAll(previous).show();
                    }
//                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
//                         new ShowAll(previous).show();

                return;


                 
            }
        });
        
        addAll(nom, prenom,numero, btnValider);
         new ShowAll(previous).show();
//      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
}