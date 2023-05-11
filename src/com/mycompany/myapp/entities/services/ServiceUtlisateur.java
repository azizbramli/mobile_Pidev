/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;
import Produit.gui.User;
import com.codename1.io.CharArrayReader;
import  com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.URL;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;

import java.util.Map;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.BaseForm;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SignInForm;
import com.codename1.ui.events.ActionListener;
import com.mycompany.gui.SessionManager;

import com.mycompany.gui.ProfileForm;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author chayma 
 */
public class ServiceUtlisateur {
    
    //singleton 
    public static ServiceUtlisateur  instance = null;
    public static boolean resultOK = true;
    String json;
    //initialisation connection request 
    private ConnectionRequest req;   
    public static ServiceUtlisateur getInstance() {
        if (instance == null)
            instance= new ServiceUtlisateur ();
        return instance;
        
    }
    
   public ServiceUtlisateur (){
       req = new ConnectionRequest(); 
   }
   //ajout de l'utlisateur
   
   
  public void ajouteruser(User u){
       String url = Statics.BASE_URL + "/registerJSON/new?email=" + u.getEmail() + "&password=" + u.getPassword() + "&nom=" + u.getNom()+ "&prenom=" + u.getPrenom()+ "&tel=" + u.getTel()+ "&nomSup=" +u.getNomSup()+ "&adresseSup=" +u.getAdresseSup();  
  req.setUrl(url);
  req.addResponseListener((e)->{
      
  String str = new String (req.getResponseData()); //Response json 
  System.out.println("data == "+str);
  });
  NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 requete sinon yet3ada chay  
}
  
  
  //login action 
  public void login(TextField email,TextField password,Resources rs) {
     
        
         String url = Statics.BASE_URL+"/signIn?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener((NetworkEvent e) ->{
         
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            try {
            
            if(json.equals("failed")) {
                
                
                
                Dialog.show("Echec d'authentification","Email or password incorrect","OK",null);
              
                
            }
            else {
                
                System.out.println("data =="+json);
             
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println(user.get("id"));
                
               float id = Float.parseFloat(user.get("id").toString());
                
               SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
               SessionManager.setPassword(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
               SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());  
                SessionManager.setTel((int) user.get("tel"));
                SessionManager.setPrenom(user.get("nomSup").toString());  
                SessionManager.setPrenom(user.get("adresseSup").toString());  
                //SessionManager.setProfilepicture(user.get("profilepicture").toString());
                  
                     if(user.size() >0 )  // l9a user
                         
                         
                        // new BaseForm().show();
                        
                     new ProfileForm(rs).show();
                     
  
                     
                     
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
   
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
  
  //modifier user 
  public static void EditUser(String email,String nom,String prenom,int tel,String nomSup,String adresseSup){
        
    String url = Statics.BASE_URL+"/modifieruser/"+SessionManager.getId()+"?email="+email+"&nom="+nom+"&prenom="+prenom+"&tel="+tel+"&nomSup="+tel+"&adresseSup="+adresseSup;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                
               req.addArgument("id", String.valueOf(SessionManager.getId()));

                req.addArgument("email", email);
                 req.addArgument("nom", nom);
                req.addArgument("prenom", prenom);
                 req.addArgument("tel", String.valueOf(tel));
                req.addArgument("nomSup", nomSup);
                req.addArgument("adresseSup", adresseSup);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                       
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
  
  //deleter a user 
  
  
  
  
  
   public boolean deleteUser (int id ) {
        String url = Statics.BASE_URL +"/deleteuser?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
   
   
   //affichage 
   
    
    public ArrayList<User>affichageUsers() {
        ArrayList<User> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/allUsers";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object> mapUsers= jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUsers.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        User u = new User();
                        
                        //dima id fi codename one float 5outhouha
                        float id = (int)Float.parseFloat(obj.get("id").toString());
                        
                        String email = obj.get("email").toString();
                     String nom = obj.get("nom").toString();
                         String prenom=obj.get("prenom").toString();
                        
                        float tel = (int)Float.parseFloat(obj.get("tel").toString());
                        String nomSup=obj.get("nomSup").toString();
                        String adresseSup=obj.get("adresseSup").toString();
                        
                        u.setId((int)id);
                        u.setEmail(email);
                        u.setTel((int)tel);
                        u.setNom(nom);
                        u.setPrenom(prenom);
                        u.setNom(nomSup);
                        u.setPrenom(adresseSup);
                        
                        
                        
                        //insert data into ArrayList result
                        result.add(u);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }

   
   
  
 
  public String  getPasswordByEmail(String  email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                    
             
                    System.out.println("data =="+json);
                    Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                   
                    
                    
                     
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

   
}

