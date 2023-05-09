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

public class produitService {
    
    public static boolean resultOk = true;
    public static produitService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<produit> listReclamations;

    

    private produitService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static produitService getInstance() {
        if (instance == null) {
            instance = new produitService();
        }
        return instance;
    }
    
    
        public boolean addTask(produit t) {

            String id_p = t.getId_p();
            String name = t.getNom_p();
            String type = t.getType_p();
            String prix_p = t.getPrix_p();
            String stock_p = t.getStock_p();

            
            String url = Statics.BASE_URL + "/mobilep/addproduit/JSON?id_p="+t.getId_p()
                                           +"&nom_p="+t.getNom_p()
                                           +"&type_p="+t.getType_p()
                                           +"&prix_p="+t.getPrix_p()
                                           +"&stock_p="+t.getStock_p();
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

    
    public ArrayList<produit> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobilep/allproduits");
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

    private ArrayList<produit> getList() {
        JSONParser jsonp ;
            jsonp = new JSONParser();
        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                produit reclamation = new produit();
                
                        float id = Float.parseFloat(obj.get("id").toString());
                        reclamation.setId_p((String)obj.get("id_p").toString());
                        reclamation.setNom_p((String)obj.get("nom_p").toString());
                        reclamation.setType_p((String)obj.get("type_p").toString());
                        reclamation.setPrix_p((String)obj.get("prix_p").toString());
                        reclamation.setStock_p((String)obj.get("stock_p").toString());
                       
                        reclamation.setId((int)id);

                listReclamations.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }
    
    public int add(produit reclamation) {
        return manage (reclamation);
    }

    public int edit(produit reclamation) {
        return manage(reclamation);
    }

    public int manage(produit reclamation) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
                                                              
            cr.setUrl(Statics.BASE_URL + "/mobilep/updateproduit/?id="+(int)reclamation.getId()+"&nom_p="+reclamation.getNom_p()+"&prix_p="+reclamation.getPrix_p());
            cr.addArgument("id", String.valueOf(reclamation.getId()));

        
        cr.addArgument("nom", reclamation.getNom_p()); 
        cr.addArgument("prix", reclamation.getPrix_p());
        
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
    
    
     public boolean modifier(produit reclamation) {
        String url = Statics.BASE_URL +"/mobilep/updateproduit/"+(int)reclamation.getId()+"?nom_p="+reclamation.getNom_p()+"&prix_p="+reclamation.getPrix_p();
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
        cr.setUrl(Statics.BASE_URL + "/mobilep/deleteproduit/"+id);
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
