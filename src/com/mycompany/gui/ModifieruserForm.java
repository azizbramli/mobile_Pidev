/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Produit.gui.User;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author chayma
 */
public class ModifieruserForm extends BaseForm{
    Form current;
    public ModifieruserForm(Resources res, User u ){
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false); 
        super.addSideMenu(res);
        TextField email = new TextField(u.getEmail(),"email",20,TextField.ANY);
        TextField name = new TextField(u.getPrenom(),"name",20,TextField.ANY);
        TextField lastname = new TextField(u.getNom(),"lastname",20,TextField.ANY);
        TextField password = new TextField(u.getEmail(),"password",20,TextField.PASSWORD);
        //TextField profilepicture = new TextField(u.getProfilepicture(),"Profilepicture",20,TextField.ANY);
        
  email.setUIID("NewsTopLine");
  name.setUIID("NewsTopLine");
  lastname.setUIID("NewsTopLine");
  password.setUIID("NewsTopLine");
  //profilepicture.setUIID("NewsTopLine");
  
  
  
  email.setSingleLineTextArea(true);
  name.setSingleLineTextArea(true);
  lastname.setSingleLineTextArea(true);
  password.setSingleLineTextArea(true);
  //profilepicture.setSingleLineTextArea(true);
   
  Button btnModifier = new Button("Edit");
  btnModifier.setUIID("Button");
  btnModifier.addPointerPressedListener(l-> {
      
      u.setEmail(email.getText());
      u.setPrenom(name.getText());
      u.setNom(lastname.getText());
      u.setPassword(password.getText());
      //u.setProfilepicture(profilepicture.getText());
     
      Button btnAnnuler = new Button("Cancel");
      btnAnnuler.addActionListener(e->{
          new ProfileForm(res).show();
      });
      Label l2 = new Label("");
      Label l3 = new Label("");
Label l4 = new Label("");
Label l5 = new Label("");
Label l1 = new Label("");
      
      Container content = BoxLayout.encloseY(
         l1,l2,     
        new FloatingHint(email),
        createLineSeparator(),
        new FloatingHint(name),
        createLineSeparator(),
        new FloatingHint(lastname),
        createLineSeparator(),
        new FloatingHint(password),
        createLineSeparator(),
        //new FloatingHint(profilepicture),
        //createLineSeparator(),
        l4,l5,
        btnModifier,
        btnAnnuler
              
      );
      
      add(content);
      show();
      
  }); 
   
   
    }
    
    
    
}
