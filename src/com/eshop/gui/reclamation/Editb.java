/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.eshop.entities.blog;
import com.eshop.services.blogService;

/**
 *
 * @author dot
 */
public class Editb extends Form{
    
    Form previous;
    
    
      public Editb (Form previous, blog r ) {
        
            super("Modifier blog", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
          //  TextField id_p = new TextField(r.getId_p(), "id_p");
        //id_p.getStyle().setFgColor(100000);
            TextField title= new TextField(r.getTitle(), "titre");
            TextField content= new TextField(r.getContent(), "contenue");
            TextField activated= new TextField(r.getActivated(), "activated");
           
        
         Button btnModifier = new Button("Edit");
         Button btnAnnuler = new Button("Annuler");
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                //Label lid_p=new Label("id_p: "+id_p.getText());
                Label ltitle=new Label("titre: "+title.getText());
                Label lcontent=new Label("contenue : "+content.getText());
                Label lactivated=new Label("activated: "+activated.getText());
              
                
               // f2.add(lid_p);
                f2.add(ltitle);
                f2.add(lcontent);
                f2.add(lactivated); 
             
                f2.show();
                      
              r.setTitle(title.getText());
              r.setContent(content.getText());     
     
              if(blogService.getInstance().modifier(r)) { 
                new ShowAllb(previous).show();
              }            
            }
        });
            addAll(title,content,activated,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAllb(previous).show();
         });
    }
    
    
    
    
}
