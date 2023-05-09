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
import com.eshop.entities.produit;
import com.eshop.services.produitService;

/**
 *
 * @author dot
 */
public class Editp extends Form{
    
    Form previous;
    
    
      public Editp (Form previous, produit r ) {
        
            super("Modifier transaction", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
          //  TextField id_p = new TextField(r.getId_p(), "id_p");
        //id_p.getStyle().setFgColor(100000);
            TextField nom_p= new TextField(r.getNom_p(), "nom_p");
            TextField type_p= new TextField(r.getType_p(), "type_p");
            TextField prix_p= new TextField(r.getPrix_p(), "prix_p");
            TextField stock_p= new TextField(r.getStock_p(), "stock_p");
        
         Button btnModifier = new Button("Edit");
         Button btnAnnuler = new Button("Annuler");
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                //Label lid_p=new Label("id_p: "+id_p.getText());
                Label lnom_p=new Label("nom_p: "+nom_p.getText());
                Label ltype_p=new Label("type_p : "+type_p.getText());
                Label lprix_p=new Label("prix_p: "+prix_p.getText());
                Label lstock_p=new Label("stock_p: "+stock_p.getText());
                
               // f2.add(lid_p);
                f2.add(lnom_p);
                f2.add(ltype_p);
                f2.add(lprix_p); 
                f2.add(lstock_p);
                f2.show();
                      
              r.setNom_p(nom_p.getText());
              r.setPrix_p(prix_p.getText());     
     
              if(produitService.getInstance().modifier(r)) { 
                new ShowAllp(previous).show();
              }            
            }
        });
            addAll(nom_p,type_p,prix_p,stock_p,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAllp(previous).show();
         });
    }    
}
