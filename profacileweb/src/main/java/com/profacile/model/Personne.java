package com.profacile.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.
GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "personnes")
public class Personne implements
Serializable {
@Id @GeneratedValue
private Long num;
private String nom;
private String prenom;
private static final long
serialVersionUID = 1L;
public Personne() {
}
public Personne(String nom,
String prenom) {
	this.nom = nom;
	this.prenom = prenom;
	}
	public Long getNum() {
	return num;
	}
	public void setNum(Long num) {
	this.num = num;
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
	public void setPrenom(String
	prenom) {
	this.prenom = prenom;
	}
}
