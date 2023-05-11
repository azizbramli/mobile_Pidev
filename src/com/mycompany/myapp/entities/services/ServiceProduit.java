/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceProduit {

    public ArrayList<Produit> produits;
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    


    public ArrayList<Produit> parseProduits(String jsonText){
                try {

                    System.out.println(jsonText);
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit a = new Produit();
                                
               a.setId((int)Float.parseFloat(obj.get("id").toString()));
                a.setLibelle(obj.get("libelle").toString());
                  a.setStock((int)Float.parseFloat(obj.get("stock").toString()));
                a.setPrix(Float.parseFloat(obj.get("prix").toString()));
                a.setImageFile(obj.get("imageFile").toString());
               a.setDateexpiration(obj.get("dateexpiration").toString());
                 a.setPrixachat(Float.parseFloat(obj.get("prixachat").toString()));               
                produits.add(a);


            }
        } catch (IOException ex) {
            
        }
        return produits;
    }
    public ArrayList<Produit> getAllProduits(){
        String url = Statics.BASE_URL+"/Allproduit";
        req.setUrl(url);
        req.setPost(false); 
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }

   

    public boolean addProduit(Produit u) {
       String url = "http://127.0.0.1:8000/addProduitJSON/new?libelle=" + u.getLibelle() + "&stock=" + u.getStock() + "&prix=" + u.getPrix() + "&dateexpiration=" + u.getDateexpiration()  +"&imageFile=" + u.getImageFile()+ "&prixachat=" + u.getPrixachat();
       
    
        req.setUrl(url);
        req.setPost(false);
        //req.addArgument("title", f.getTitle());

      
               System.out.println(url);
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

        public boolean editProduit(Produit u) {
           
      String url = "http://127.0.0.1:8000/updateProduitJSON/"+u.getId()  + "?libelle=" + u.getLibelle() + "&stock=" + u.getStock() + "&prix=" + u.getPrix() + "&dateexpiration=" + u.getDateexpiration() +  "&imageFile=" + u.getImageFile()+ "&prixachat=" + u.getPrixachat();
               req.setUrl(url);
               req.setPost(false); 
               System.out.println(url);
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

    public boolean deleteProduit(int id) {
        String url = "http://127.0.0.1:8000/deleteProduitJSON/" + id;
               req.setUrl(url);
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

}

