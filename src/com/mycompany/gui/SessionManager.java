/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author raoun
 */
public class SessionManager {
    
      public static Preferences pref ;
      
        private static int id ;
    private static String email;
    private static String password;
    private static String nom;
     private static String prenom;
    private static int tel;
    private  static String nomSup;
    private static String adresseSup;
    
    public static Preferences getPref() {
        return pref;
    }

    public static int getId() {
         return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static String getEmail() {
         return Preferences.get("email",email);
    }

    public static void setEmail(String email) {
        pref.set("email",email);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
        pref.set("password", password);
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
        pref.set("nom",nom);
    }

    public static String getPrenom() {
        return pref.get("prenom",prenom);
    }

    public static void setPrenom(String prenom) {
        pref.set("prenom",prenom);
    }

    public static int getTel() {
       return pref.get("tel",tel);
    }

    public static void setTel(int tel) {
        pref.set("tel",tel);
    }

    public static String getNomSup() {
        return pref.get("nomSup",nomSup);
    }

    public static void setNomSup(String nomSup) {
        pref.set("nomSup",nomSup);
    }

    public static String getAdresseSup() {
        return pref.get("adresseSup",adresseSup);
    }

    public static void setAdresseSup(String adresseSup) {
       pref.set("adresseSup",adresseSup);
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }
}

    