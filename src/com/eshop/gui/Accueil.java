package com.eshop.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.eshop.gui.reclamation.ShowAll;
import com.eshop.gui.reclamation.ShowAllO;
import com.eshop.gui.reclamation.ShowAllU;
import com.eshop.gui.reclamation.ShowAllb;
import com.eshop.gui.reclamation.ShowAllp;

public class Accueil extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public Accueil() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Accueil");
        label.setUIID("links");

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeusersButton(),
                makeReclamationsButton(),
                makeOrdonnacesButton(),
                makeproduitButton(),
                makeblogButton()
                
                
        );

        this.add(menuContainer);
    }
    
    private Button makeReclamationsButton() {
        Button button = new Button("Reservation");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAll(this).show());
        return button;
    }
    private Button makeOrdonnacesButton() {
        Button button = new Button("Ordonnance");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAllO(this).show());
        return button;
    }
     private Button makeusersButton() {
        Button button = new Button("User");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAllU(this).show());
        return button;
    }
      private Button makeblogButton() {
        Button button = new Button("Blog");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAllb(this).show());
        return button;
    }
       private Button makeproduitButton() {
        Button button = new Button("Produits");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAllp(this).show());
        return button;
    }
}