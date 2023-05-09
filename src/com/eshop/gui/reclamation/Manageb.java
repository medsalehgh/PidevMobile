package com.eshop.gui.reclamation;


import com.codename1.components.InfiniteProgress;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
//import com.codename1.ui.FontImage;


public class Manageb extends Form {
    Form previous;
  
      public Manageb (Form previous) {
          super("Ajouter blog", new BoxLayout(BoxLayout.Y_AXIS));           
          super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
        
      //  id_p.getStyle().setFgColor(100000);
        TextField title= new TextField("","titre");
        TextField content= new TextField("","content");
        TextField activated= new TextField("","activated");
       
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label ltitle=new Label("title "+title.getText());
                Label lcontent=new Label("content: "+content.getText());
                Label lactivated=new Label("activated: "+activated.getText());
              
              //  Label lDate=new Label("Date: "+date.getDate().toString());
                f2.add(ltitle);
                f2.add(lcontent);
                f2.add(lactivated);
               
                
                f2.show();
                if ((title.getText().length()==0)&&(content.getText().length()==0)&&(activated.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{           
                        blog t = new blog(title.getText(),content.getText(),activated.getText());
                        if( blogService.getInstance().addTask(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           new ShowAllb(previous).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
            
        addAll(title,content,activated,btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}