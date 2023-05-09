package com.eshop.gui.reclamation;


import com.codename1.components.InfiniteProgress;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
//import com.codename1.ui.FontImage;


public class Managep extends Form {
    Form previous;
  
      public Managep (Form previous) {
          super("Ajouter Produits", new BoxLayout(BoxLayout.Y_AXIS));           
          super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
        TextField id_p = new TextField("","id_p");
        id_p.getStyle().setFgColor(100000);
        TextField nom_p= new TextField("","nom_p");
        TextField type_p= new TextField("","type_p");
        TextField prix_p= new TextField("","prix_p");
        TextField stock_p= new TextField("","stock_p");
   
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lid_p=new Label("id: "+id_p.getText());
                Label lnom_p=new Label("nom: "+nom_p.getText());
                Label ltype_p=new Label("type: "+type_p.getText());
                Label lprix_p=new Label("prix: "+prix_p.getText());
                Label lstock_p=new Label("stock: "+stock_p.getText());
              //  Label lDate=new Label("Date: "+date.getDate().toString());
                f2.add(lid_p);
                f2.add(lnom_p);
                f2.add(ltype_p);
                f2.add(lprix_p);
                f2.add(lstock_p);
                
                f2.show();
                if ((id_p.getText().length()==0)&&(nom_p.getText().length()==0)&&(type_p.getText().length()==0)&&(prix_p.getText().length()==0)&&(stock_p.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{           
                        produit t = new produit(id_p.getText(),nom_p.getText(),type_p.getText(),prix_p.getText(),stock_p.getText());
                        if( produitService.getInstance().addTask(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           new ShowAllp(previous).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
            
        addAll(id_p,nom_p,type_p,prix_p,stock_p,btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}