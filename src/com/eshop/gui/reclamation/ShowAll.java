package com.eshop.gui.reclamation;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.eshop.entities.Reclamation;
import com.eshop.services.ReclamationService;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowAll extends Form {

    Form previous; 
    
    public static Reclamation currentReclamation = null;
    Button addBtn;

    public ShowAll(Form previous) {
        super("Reservation", new BoxLayout(BoxLayout.Y_AXIS));
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
        
        
        Style modifierStyle = new Style(addBtn.getUnselectedStyle());
       modifierStyle.setFgColor(0x0FF4F9); // Couleur verte pour l'icône Modifier

        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle); // Utilisation de l'icône Modifier
        addBtn.setIcon(modifierImage);
        addBtn.setTextPosition(RIGHT);
        
        this.add(addBtn);

        ArrayList<Reclamation> listReclamations = ReclamationService.getInstance().getAll();

        for ( Reclamation rec : listReclamations) {
            System.out.println(" rec :"+ rec);
        };
            
        
        if (listReclamations.size() > 0) {
            for (Reclamation reclamation : listReclamations) {
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
            new Manage(this).show();
        });
        
    }
    Label  lid, lnom,lprenom,lnumero;
    
    

    private Container makeModelWithoutButtons(Reclamation reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");
        
        lid = new Label("id : " + reclamation.getId());
        lid.setUIID("labelDefault");
        
        lnom = new Label("nom: " + reclamation.getNom());
        lnom.setUIID("labelDefault");
        
        lprenom = new Label("prenom : " + reclamation.getPrenom());
        lprenom.setUIID("labelDefault");
        
        
        lnumero= new Label("numero : " + reclamation.getNumero());
        lnumero.setUIID("labelDefault");
        
        
        
        
        
      
        
       
        reclamationModel.addAll(                
             lid, lnom,lprenom,lnumero
        );

        return reclamationModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(Reclamation reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
    
        
           
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        
        
        
            Style modifierStyle = new Style(editBtn.getUnselectedStyle());
       modifierStyle.setFgColor(0x008000); // Couleur verte pour l'icône Modifier

        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle); // Utilisation de l'icône Modifier
        editBtn.setIcon(modifierImage);
        editBtn.setTextPosition(RIGHT);
        
        editBtn.addActionListener(action -> {
            currentReclamation = reclamation;
            new Edit(previous, reclamation).show();
        });
      
        
        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        
        Style supprmierStyle = new Style(deleteBtn.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        deleteBtn.setIcon(suprrimerImage);
        deleteBtn.setTextPosition(RIGHT);
        
        
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
              ReclamationService.getInstance().delete(reclamation.getId());         
                new ShowAll(previous).show();
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