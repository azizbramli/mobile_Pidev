/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author msi
 */
public class Produit {
    
    private int id;
    private String libelle;
    private int stock;
    private float prix;
    
    private String dateexpiration;
    private String imageFile;
     private float prixachat;

    public Produit() {
    }

    public Produit(int id, String libelle, int stock, float prix, String dateexpiration, String imageFile, float prixachat) {
        this.id = id;
        this.libelle = libelle;
        this.stock = stock;
        this.prix = prix;
        this.dateexpiration = dateexpiration;
        this.imageFile = imageFile;
        this.prixachat = prixachat;
    }

    public Produit(String libelle, int stock, float prix, String dateexpiration, String imageFile, float prixachat) {
        this.libelle = libelle;
        this.stock = stock;
        this.prix = prix;
        this.dateexpiration = dateexpiration;
        this.imageFile = imageFile;
        this.prixachat = prixachat;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getStock() {
        return stock;
    }

    public float getPrix() {
        return prix;
    }

    public String getDateexpiration() {
        return dateexpiration;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrixachat() {
        return prixachat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDateexpiration(String dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public void setPrixachat(float prixachat) {
        this.prixachat = prixachat;
    }

   

}
