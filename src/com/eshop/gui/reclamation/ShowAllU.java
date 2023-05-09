package com.eshop.gui.reclamation;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.eshop.entities.users;
import com.eshop.services.UserService;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowAllU extends Form {

    Form previous; 
    
    public static users currentReclamation = null;
    Button addBtn;

    public ShowAllU(Form previous) {
        super("User", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);

        ArrayList<users> listReclamations = UserService.getInstance().getAll();

        for ( users rec : listReclamations) {
            System.out.println(" rec :"+ rec);
        };
            
        
        if (listReclamations.size() > 0) {
            for (users reclamation : listReclamations) {
                Component model = makeReclamationModel(reclamation);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        addBtn.addActionListener(action -> {
            currentReclamation = null;
            new ManageU(this).show();
        });
        
    }
    Label  lid, lemail,lpassword;
    
    

    private Container makeModelWithoutButtons(users reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");
        
        lid = new Label("id : " + reclamation.getId());
        lid.setUIID("labelDefault");
        
        lemail = new Label("Email: " + reclamation.getEmail());
        lemail.setUIID("labelDefault");
        
        lpassword = new Label("Password : " + reclamation.getPassword());
        lpassword.setUIID("labelDefault");
        
        
        
      
        
       
        reclamationModel.addAll(                
             lid, lemail,lpassword
        );

        return reclamationModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(users reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        
        editBtn.addActionListener(action -> {
            currentReclamation = reclamation;
            new EditU(previous, reclamation).show();
        });
      
        
        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce Transaction ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            
            btnConfirm.addActionListener(actionConf -> {
              System.out.println("reclamation ="+reclamation);
              System.out.println("ID  ="+reclamation.getId());
              UserService.getInstance().delete(reclamation.getId());         
                new ShowAllU(previous).show();
            });
            
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        reclamationModel.add(btnsContainer);

        return reclamationModel;
    }
    
}