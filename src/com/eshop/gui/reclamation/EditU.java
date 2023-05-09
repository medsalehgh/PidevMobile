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
import com.eshop.entities.users;
import com.eshop.services.UserService;

public class EditU  extends Form {
    
    //Modif
      Form previous; 
    public EditU (Form previous, users r ) {
        
            super("Modifier user", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
            TextField email = new TextField(r.getEmail(), "email");
            TextField password = new TextField(r.getPassword(), "password");          
            
        
         Button btnModifier = new Button("Edit");
         Button btnAnnuler = new Button("Annuler");
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lemail=new Label("Email: "+email.getText());
                Label lpassword=new Label("Password: "+password.getText());
                
                f2.add(lemail);
                f2.add(lpassword);
                                        
                f2.show();
                      
              r.setEmail(email.getText());
              r.setPassword(password.getText()); 
              
              
     
             /* if(UserService.getInstance().modifier(r)) { 
                new ShowAllU(previous).show();
              } */
             
             
             try {
            if(UserService.getInstance().modifier(r)) { 
                new ShowAllU(previous).show();
            } else {
                Dialog.show("Error", "Unable to update transaction", new Command("OK"));
            }
        } catch(Exception e) {
            Dialog.show("Error", "Unable to update transaction: " + e.getMessage(), new Command("OK"));
        }     
             
            }
        });
        addAll(email,password,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAllU(previous).show();
         });
    }
  
}
