package com.eshop.gui.reclamation;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
import com.eshop.services.UserService;
import com.eshop.gui.reclamation.ShowAllU;




public class ManageU extends Form {
     Form previous; 
     //add  GUI
    public ManageU (Form previous) {        
            super("Ajouter User", new BoxLayout(BoxLayout.Y_AXIS));           
             super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
           
            TextField email = new TextField("", "email");
            TextField password = new TextField("", "password");
            
            email.setConstraint(TextField.EMAILADDR);
            password.setConstraint(TextField.PASSWORD);
            
            
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lemail=new Label("email: "+email.getText());
                Label lpassword=new Label("password: "+password.getText());
                
                
                f2.add(lemail);
                f2.add(lpassword);
                              
                f2.show();
                if ((email.getText().length()==0)&&(password.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{ 
                             users t = new users(email.getText(),password.getText());
                            if( UserService.getInstance().addTask(t))
                            {
                               Dialog.show("Success","User added",new Command("OK"));
                               new ShowAllU(previous).show();
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                return;


                 
            }
        });
        
        addAll(email, password, btnValider);
//      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
}