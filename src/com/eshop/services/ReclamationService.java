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

public class ReclamationService {
  
    public static boolean resultOk = true;
    public static ReclamationService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<Reclamation> listReclamations;

    

    private ReclamationService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }
    
    
        public boolean addTask(Reclamation t) {

            
            String nom = t.getNom();
            String prenom = t.getPrenom();
            String numero = t.getNumero();

            
//            String url = Statics.BASE_URL + "/mobile/addproduit/JSON?id_p="+t.getId_p()
//                                          +"&nom_p="+t.getNom_p()
//                                          +"&type_p="+t.getType_p()
//                                          +"&prix_p="+t.getPrix_p()
//                                          +"&stock_p="+t.getStock_p();
            String url =Statics.BASE_URL+"/addReservationJSON?nom="+t.getNom()                                                                                                              
                                        +"&prenom="+t.getPrenom()
                                        +"&numero="+t.getNumero();
        
            System.out.println("url : "+ url);
            req.setUrl(url);
            req.setHttpMethod("GET");

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

    
    public ArrayList<Reclamation> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/getreserva");
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

    private ArrayList<Reclamation> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation reclamation = new Reclamation();
                
                      float id = Float.parseFloat( obj.get("id").toString() ) ; 
                      
                        reclamation.setNom((String)obj.get("nom").toString());
                        reclamation.setPrenom((String)obj.get("prenom").toString());
                        reclamation.setNumero((String)obj.get("numero").toString());
                 
                        
                        //System.out.println("reclamation test :  "+ reclamation);
                        
                         
                        reclamation.setId((int)id);
                
                        listReclamations.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }
    
    public int add(Reclamation reclamation) {
        return manage(reclamation);
    }

    public int edit(Reclamation reclamation) {
        return manage(reclamation );
    }

    public int manage(Reclamation reclamation) {
        
        cr = new ConnectionRequest();
        
        cr.setHttpMethod("GET");                                               
            cr.setUrl(Statics.BASE_URL + "/updateuser/?id="+(int)reclamation.getId()+"&nom="+reclamation.getNom()+"&prenom="+reclamation.getPrenom()+"&numero="+reclamation.getNumero());
            cr.addArgument("id", String.valueOf(reclamation.getId()));
     
        
        //cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        
        cr.addArgument("nom", reclamation.getNom());
        cr.addArgument("prenom", reclamation.getPrenom());
        cr.addArgument("numero", reclamation.getNumero());

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
    public boolean modifier(Reclamation reclamation) {
        String url = Statics.BASE_URL +"/editreserv"+"?nom="+reclamation.getNom()+"&prenom="+reclamation.getPrenom()+"&numero="+reclamation.getNumero()+"&id="+(int)reclamation.getId();
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
        cr.setUrl(Statics.BASE_URL + "/deleteRes/"+id);
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
