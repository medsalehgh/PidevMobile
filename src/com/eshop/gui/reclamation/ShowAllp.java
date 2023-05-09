package com.eshop.gui.reclamation;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.eshop.entities.produit;
import com.eshop.services.produitService;

import java.text.SimpleDateFormat;
import java.util.*;

public class ShowAllp extends Form {

    Form previous; 
    
    public static produit currentReclamation = null;
    Button addBtn;

    public ShowAllp(Form previous) {
        super("Produits", new BoxLayout(BoxLayout.Y_AXIS));
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

        ArrayList<produit> listReclamations = produitService.getInstance().getAll();

        for ( produit rec : listReclamations) {
            System.out.println(" rec :"+ rec);
        };
        
        if (listReclamations.size() > 0) {
            for (produit reclamation : listReclamations) {
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
            new Managep(this).show();
        });
        
    }
    Label lid,lidp,lnom,ltype,lprix,lstock;
    //Label dateLabel   , typeLabel   , etatLabel   , descriptionLabel  ;
    

    private Container makeModelWithoutButtons(produit reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");
        
        lid = new Label("id : " + reclamation.getId());
        lid.setUIID("labelDefault");
        
        lidp = new Label("idprod : " + reclamation.getId_p());
        lidp.setUIID("labelDefault");
        
        lnom = new Label("nom : " + reclamation.getNom_p());
        lnom.setUIID("labelDefault");
        
        ltype = new Label("Type : " + reclamation.getType_p());
        ltype.setUIID("labelDefault");
        
        lprix = new Label("prix : " + reclamation.getPrix_p());
        lprix.setUIID("labelDefault");
        
        lstock = new Label("stock : " + reclamation.getStock_p());
        lstock.setUIID("labelDefault");
      
        
        reclamationModel.addAll(
                
                lid,lidp,lnom,ltype,lprix,lstock 
                
        );

        return reclamationModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(produit reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
//        Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
//        Image profilePic = res.getImage("2.png");
//        Image mask = res.getImage("round-mask.png");
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
//        profilePicLabel.setMask(mask.createMask());
//        
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        
        Style modifierStyle = new Style(editBtn.getUnselectedStyle());
       modifierStyle.setFgColor(0x008000); // Couleur verte pour l'icône Modifier

        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle); // Utilisation de l'icône Modifier
        editBtn.setIcon(modifierImage);
        editBtn.setTextPosition(RIGHT);
        
        editBtn.addActionListener(action -> {
            currentReclamation = reclamation;
            new Editp(previous, reclamation).show();  
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
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce reclamation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            
            btnConfirm.addActionListener(actionConf -> {
              System.out.println("reclamation ="+reclamation);
              System.out.println("ID  ="+reclamation.getId());
              produitService.getInstance().delete(reclamation.getId());         
                new ShowAllp(previous).show();
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