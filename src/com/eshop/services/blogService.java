package com.eshop.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.eshop.entities.*;
import com.eshop.utils.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class blogService {
    
    public static boolean resultOk = true;
    public static blogService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<blog> listReclamations;

    

    private blogService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static blogService getInstance() {
        if (instance == null) {
            instance = new blogService();
        }
        return instance;
    }
    
    
        public boolean addTask(blog t) {

            String title = t.getTitle();
            String content = t.getContent();
            String activated = t.getActivated();
           

            
            String url = Statics.BASE_URL + "/addpost/JSON?title="+t.getTitle()
                                           +"&content="+t.getContent()
                                           +"&activated="+t.getActivated()
                                         ;
            System.out.println("url : "+ url);
            req.setUrl(url);
            req.setHttpMethod("POST");

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        }

    
    public ArrayList<blog> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/getpost");
        System.out.println("url = "+cr);

        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listReclamations = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReclamations;
    }

    private ArrayList<blog> getList() {
        JSONParser jsonp ;
            jsonp = new JSONParser();
        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                blog reclamation = new blog();
                
                        float id = Float.parseFloat(obj.get("id").toString());
                        reclamation.setTitle((String)obj.get("title").toString());
                        reclamation.setContent((String)obj.get("content").toString());
                        reclamation.setActivated((String)obj.get("activated").toString());
                       
                       
                        reclamation.setId((int)id);

                listReclamations.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }
    
    public int add(blog reclamation) {
        return manage (reclamation);
    }

    public int edit(blog reclamation) {
        return manage(reclamation);
    }

    public int manage(blog reclamation) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
                                                              
            cr.setUrl(Statics.BASE_URL + "/updatepost/?id="+(int)reclamation.getId()+"&title="+reclamation.getTitle()+"&content="+reclamation.getContent());
            cr.addArgument("id", String.valueOf(reclamation.getId()));

        
        cr.addArgument("nom", reclamation.getTitle()); 
        cr.addArgument("prix", reclamation.getContent());
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }
    
    
     public boolean modifier(blog reclamation) {
        String url = Statics.BASE_URL +"/updatepost/"+(int)reclamation.getId()+"?title="+reclamation.getTitle()+"&content="+reclamation.getContent();
        req.setUrl(url);
        System.out.println("url modif : "+url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }

    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deletepost/"+id);
        System.out.println("url delete : "+cr);
        cr.setHttpMethod("POST");
     //   cr.addArgument("id", String.valueOf(id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });
        
        

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
