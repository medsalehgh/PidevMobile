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

public class OrdonanceService {
  
    public static boolean resultOk = true;
    public static OrdonanceService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<Ordonance> listReclamations;

    

    private OrdonanceService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static OrdonanceService getInstance() {
        if (instance == null) {
            instance = new OrdonanceService();
        }
        return instance;
    }
    
    
        public boolean addTask(Ordonance t) {

            String idOrdonnance = t.getIdOrdonnance();
            String nomMedicaments = t.getNomMedicaments();
            String nomPatient = t.getNomPatient();
            String signatureMedecin = t.getSignatureMedecin();
            
            
//            String url = Statics.BASE_URL + "/mobile/addproduit/JSON?id_p="+t.getId_p()
//                                          +"&nom_p="+t.getNom_p()
//                                          +"&type_p="+t.getType_p()
//                                          +"&prix_p="+t.getPrix_p()
//                                          +"&stock_p="+t.getStock_p();
            String url =Statics.BASE_URL+"/addordonnance/JSON/?idOrdonnance="+t.getIdOrdonnance()+"&nomMedicaments="+t.getNomMedicaments() +"&signatureMedecin="+t.getSignatureMedecin()                                                                                                              
                                        +"&nomPatient="+t.getNomPatient(); 
        
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

    
    public ArrayList<Ordonance> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/getOrdonnance");
        System.out.println("url = "+cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("nnnnnnnnnnnnnnnnnnnnnn");

                if (cr.getResponseCode() == 200) {
                    listReclamations = getList();   
                }
                System.out.println("ffffffffffff");
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReclamations;
    }

    private ArrayList<Ordonance> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Ordonance reclamation = new Ordonance();
                
                      float id = Float.parseFloat( obj.get("id").toString() ) ; 
                        reclamation.setIdOrdonnance((String)obj.get("idOrdonnance").toString());
                        reclamation.setNomMedicaments((String)obj.get("nomMedicaments").toString());
                        reclamation.setNomPatient((String)obj.get("nomPatient").toString());
                        reclamation.setSignatureMedecin((String)obj.get("signatureMedecin").toString());
                        
                        System.out.println("reclamation test :  "+ reclamation);
                        
                         
                        reclamation.setId((int)id);
                
                        listReclamations.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }
    
    public int add(Ordonance reclamation) {
        return manage(reclamation);
    }

    public int edit(Ordonance reclamation) {
        return manage(reclamation );
    }

    public int manage(Ordonance reclamation) {
        
        cr = new ConnectionRequest();
        
        cr.setHttpMethod("GET");                                               
            cr.setUrl(Statics.BASE_URL + "/updateuser/{3}/?id="+(int)reclamation.getId()+"&idOrdonnance="+reclamation.getIdOrdonnance()+"&nomMedicaments="+reclamation.getNomMedicaments()+"&signatureMedecin="+reclamation.getSignatureMedecin()+"&nomPatient="+reclamation.getNomPatient());
            cr.addArgument("id", String.valueOf(reclamation.getId()));
     
        
        //cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        cr.addArgument("idOrdonnance", reclamation.getIdOrdonnance());
        cr.addArgument("nomMedicaments", reclamation.getNomMedicaments());
        cr.addArgument("nomPatient", reclamation.getNomPatient());
        cr.addArgument("signatureMedecin", reclamation.getSignatureMedecin());
        
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
    public boolean modifier(Ordonance reclamation) {
        String url = Statics.BASE_URL +"/editordonnance/?id="+(int)reclamation.getId()+"&idOrdonnance="+reclamation.getIdOrdonnance()+"&nomMedicaments="+reclamation.getNomMedicaments()+"&signatureMedecin="+reclamation.getSignatureMedecin()+"&nomPatient="+reclamation.getNomPatient();
        req.setUrl(url);
        System.out.println("url modif : "+url);
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
    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deleteordonnance/"+id);
        System.out.println("url delete : "+cr);
        cr.setHttpMethod("GET");
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
 