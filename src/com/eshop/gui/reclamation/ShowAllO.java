package com.eshop.gui.reclamation;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.eshop.entities.Ordonance;
import com.eshop.services.OrdonanceService;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowAllO extends Form {

    Form previous; 
    
    public static Ordonance currentReclamation = null;
    Button addBtn;

    public ShowAllO(Form previous) {
        super("Ordonnance", new BoxLayout(BoxLayout.Y_AXIS));
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

        ArrayList<Ordonance> listReclamations = OrdonanceService.getInstance().getAll();

        for ( Ordonance rec : listReclamations) {
            System.out.println(" rec :"+ rec);
        };
            
        
        if (listReclamations.size() > 0) {
            for (Ordonance reclamation : listReclamations) {
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
            new ManageO(this).show();
        });
        
    }
    Label  lid, lidOrdonnance,lnomMedicaments,lnomPatient, lsignatureMedecin;
    
    

    private Container makeModelWithoutButtons(Ordonance reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");
        
        lid = new Label("id : " + reclamation.getId());
        lid.setUIID("labelDefault");
        
        lidOrdonnance=new Label("idOrdonnance : " + reclamation.getId());
        lidOrdonnance.setUIID("labelDefault");
        
        
        lnomMedicaments = new Label("nomMedicaments : " + reclamation.getNomMedicaments());
        lnomMedicaments.setUIID("nomMedicaments");
        
        lnomPatient = new Label("nomPatient  : " + reclamation.getNomPatient());
        lnomPatient.setUIID("labelDefault");
        
        lsignatureMedecin = new Label("signatureMedecin  : " + reclamation.getNomPatient());
        lsignatureMedecin.setUIID("labelDefault");
        
        
        
        
      
        
       
        reclamationModel.addAll(                
            lid, lidOrdonnance,lnomMedicaments,lnomPatient, lsignatureMedecin
        );

        return reclamationModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(Ordonance reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        
        editBtn.addActionListener(action -> {
            currentReclamation = reclamation;
            new EditO(previous, reclamation).show();
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
              OrdonanceService.getInstance().delete(reclamation.getId());         
                new ShowAllO(previous).show();
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