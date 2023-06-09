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

import Produit.gui.User;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.services.ServiceUtlisateur;

import java.io.IOException;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
    private String avatar=null;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
         
                
        TextField email = new TextField("", "email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
        TextField tel = new TextField("", "tel", 20, TextField.ANY);
        TextField nomSup= new TextField("", "nomSup", 20, TextField.ANY);
        TextField adresseSup= new TextField("", "adresseSup", 20, TextField.ANY);

        
         ScaleImageLabel img_viewer = new ScaleImageLabel();

     
        
        
        
        
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
        nomSup.setSingleLineTextArea(false);
        adresseSup.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");


     
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
    
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                
                
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                 new FloatingHint(prenom),
                createLineSeparator(),
                 new FloatingHint(tel),
                createLineSeparator(),
                new FloatingHint(nomSup),
                createLineSeparator(),
                 new FloatingHint(adresseSup),
                createLineSeparator()
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.addActionListener((e) -> {
             
         try {
             if(email.getText() =="" || password.getText() =="") {
                 Dialog.show("Veuillez vérifier les doneés","","Annuler","OK");
             }
             else {
                 InfiniteProgress ip = new InfiniteProgress();
                 //final Dialog iDialog = ip.showInfiniteBlocking();
                 User us = new User(String.valueOf(email.getText()).toString(), String.valueOf(password.getText()).toString(),String.valueOf(nom.getText()).toString(),
                         String.valueOf(prenom.getText()).toString(),Integer.parseInt(String.valueOf(tel.getText())),String.valueOf(nomSup.getText()).toString(),String.valueOf(adresseSup.getText()).toString());
                 
                 ServiceUtlisateur.getInstance().ajouteruser(us);
                 Dialog.show("Success","account is created!","OK",null);
                
               new SignInForm(res).show();
                
                 
                
                 
             }
         }catch (Exception ex){
             ex.printStackTrace();
         }
         });
             
    
    
}
    
}
