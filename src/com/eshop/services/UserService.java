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

public class UserService {
  
    public static boolean resultOk = true;
    public static UserService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<users> listReclamations;

    

    private UserService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    
        public boolean addTask(users t) {

            
            String email = t.getEmail();
            String password = t.getPassword();
            
            
//            String url = Statics.BASE_URL + "/mobile/addproduit/JSON?id_p="+t.getId_p()
//                                          +"&nom_p="+t.getNom_p()
//                                          +"&type_p="+t.getType_p()
//                                          +"&prix_p="+t.getPrix_p()
//                                          +"&stock_p="+t.getStock_p();
            String url =Statics.BASE_URL+"/users/adduser/JSON?email="+t.getEmail()                                                                                                              
                                        +"&password="+t.getPassword(); 
        
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

    
    public ArrayList<users> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/users/alluser/CarteJson");
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

    private ArrayList<users> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                users reclamation = new users();
                
                      float id = Float.parseFloat( obj.get("id").toString() ) ; 
                      
                        reclamation.setEmail((String)obj.get("email").toString());
                        reclamation.setPassword((String)obj.get("password").toString());
                        
                        //System.out.println("reclamation test :  "+ reclamation);
                        
                         
                        reclamation.setId((int)id);
                
                        listReclamations.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }
    
    public int add(users reclamation) {
        return manage(reclamation);
    }

    public int edit(users reclamation) {
        return manage(reclamation );
    }

    public int manage(users reclamation) {
        
        cr = new ConnectionRequest();
        
        cr.setHttpMethod("GET");                                               
            cr.setUrl(Statics.BASE_URL + "/users/updateuser/?id="+(int)reclamation.getId()+"&email="+reclamation.getEmail()+"&password="+reclamation.getPassword());
            cr.addArgument("id", String.valueOf(reclamation.getId()));
     
        
        //cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        
        cr.addArgument("email", reclamation.getEmail());
        cr.addArgument("password", reclamation.getPassword());
        
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

    
    //Update 
    public boolean modifier(users reclamation) {
        String url = Statics.BASE_URL +"/users/upup"+"?email="+reclamation.getEmail()+"&password="+reclamation.getPassword()+"&id="+(int)reclamation.getId();
        req.setUrl(url);
        System.out.println("url modif : "+url);
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
    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/users/deleteuser/"+id);
        System.out.println("url delete : "+cr);
        cr.setHttpMethod("POST");
        // cr.addArgument("id", String.valueOf(id));
        
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
