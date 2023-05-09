package com.eshop.entities;

import java.util.Date;
import com.eshop.utils.*;

public class blog {
    
  private int id ;    
    private String title,content ,activated;
    public blog() {}

    public blog(int id,String title,String content,String activated) {
        
        this.id = id;
        this.title = title;
        this.content = content;
        this.activated= activated;
      
       
    }

    public blog(String title,String content, String activated) {   
        this.title = title;
        this.content = content;
        this.activated=activated;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }


    @Override
    public String toString() {
        return "blog{" + "id=" + id + ", title=" + title + ", content=" + content + ", activated=" + activated + '}';
    }

  

    



    

   
    
    
    
}
