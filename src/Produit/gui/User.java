/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.gui;

import java.util.Date;

/**
 *
 * @author raoun
 */
public class User {
    private int id ;
    private String email;
    private String password;
    private String nom;
     private String prenom;
    private int tel;
    private String nomSup;
    private String adresseSup;

    public User() {
    }

    public User(int id, String email, String nom, String prenom, int tel, String nomSup, String adresseSup) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.nomSup = nomSup;
        this.adresseSup = adresseSup;
    }

    public User(int id, String email, String password, String nom, String prenom, int tel, String nomSup, String adresseSup) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.nomSup = nomSup;
        this.adresseSup = adresseSup;
    }

    public User(String email, String password, String nom, String prenom, int tel, String nomSup, String adresseSup) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.nomSup = nomSup;
        this.adresseSup = adresseSup;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", nomSup=" + nomSup + ", adresseSup=" + adresseSup + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNomSup() {
        return nomSup;
    }

    public void setNomSup(String nomSup) {
        this.nomSup = nomSup;
    }

    public String getAdresseSup() {
        return adresseSup;
    }

    public void setAdresseSup(String adresseSup) {
        this.adresseSup = adresseSup;
    }
    
    
}
    