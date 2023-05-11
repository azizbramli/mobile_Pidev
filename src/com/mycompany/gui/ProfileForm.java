/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.services.ServiceUtlisateur;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
       
        
        
        Image img = res.getImage("aa.png");
       
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Button modif = new Button("Edit My Account");
        Button supprimer = new Button("Delete My Account");
     
   
       
        add(BoxLayout.encloseY(
                sl,
                supprimer,
                modif
        
        ));        
//      Button next = new Button("next");
     //   add(BorderLayout.SOUTH, BoxLayout.encloseY(
                //next
               
       // ));
        TextField email = new TextField(SessionManager.getEmail());
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        TextField nom = new TextField(SessionManager.getNom());
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
        TextField prenom = new TextField(SessionManager.getPrenom());
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);
        TextField tel = new TextField(String.valueOf(SessionManager.getTel()));
        tel.setUIID("TextFieldBlack");
        add(tel);
        TextField nomSup = new TextField(SessionManager.getNomSup());
        nomSup.setUIID("TextFieldBlack");
        addStringValue("nomSup", nomSup);
        TextField adresseSup = new TextField(SessionManager.getAdresseSup());
        adresseSup.setUIID("TextFieldBlack");
        addStringValue("adresseSup", adresseSup);
        
        modif.addActionListener((edit)->{
           InfiniteProgress ip = new InfiniteProgress();
          final Dialog ipDlg    = ip.showInifiniteBlocking();
          ServiceUtlisateur.EditUser(email.getText(), nom.getText(), prenom.getText(), Integer.parseInt(tel.getText()), nomSup.getText(),adresseSup.getText());
           Dialog.show("Success","account is Modified!","OK",null);
            SessionManager.setEmail(email.getText());
            SessionManager.setNom(nom.getText());
            SessionManager.setPrenom(prenom.getText());
            SessionManager.setTel(Integer.parseInt(tel.getText()));
            SessionManager.setNomSup(nomSup.getText());
            SessionManager.setAdresseSup(adresseSup.getText());
          
            new ProfileForm(res).show();
               ipDlg.dispose();
            refreshTheme();
       });
           supprimer.addActionListener( l -> {
        int id=SessionManager.getId();
            Dialog dig = new Dialog("Delete");
            
            if(dig.show("Suppression","You are about to delete your account ?","Cancel","yes")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceUtlisateur.getInstance().deleteUser(id)) {
                    new SignInForm(res).show();
                }
           
        });

      
   /*  TextField name = new TextField(SessionManager.getName());
        //nom.setUIID("TextFieldWhite");
        addStringValue(" Name: ", name);
        
        TextField email= new TextField(SessionManager.getEmail());
        //nom.setUIID("TextFieldWhite");
        addStringValue(" Email: ", email);
        
         TextField password = new TextField(SessionManager.getPassword());
        //nom.setUIID("TextFieldWhite");
        addStringValue(" Password: ", password);
         TextField lastname= new TextField(SessionManager.getLastname());
        //nom.setUIID("TextFieldWhite");
        addStringValue(" Email: ", email);
         TextField profilepicture= new TextField(SessionManager.getProfilepicture());
        //nom.setUIID("TextFieldWhite");
        addStringValue(" Profilepicture: ", profilepicture);*/
         
    }
   
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

