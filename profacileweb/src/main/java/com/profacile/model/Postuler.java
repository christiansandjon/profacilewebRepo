package com.profacile.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Postuler implements Serializable{
	private static final long
	serialVersionUID = 1L;
	
	private Offre offre;
	private Professeur professeur;
	
	public Offre getOffre() {
		return this.offre;
	}
	
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	
	public Professeur getProfesseur() {
		return this.professeur;
	}
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	
	public ArrayList<Postuler> lesEleves(String email) {
		ArrayList<Postuler> a = new ArrayList<Postuler>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre,postuler WHERE offre.id = postuler.id_offre AND email_enseignant = '"+email+"' AND postuler.status = 'accepter' ORDER BY offre.id DESC";
			
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				Offre o = new Offre();
				
				int id = resultat.getInt("id");
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				int idEleve = resultat.getInt("id_eleve");
				String status = resultat.getString("status");
				String datepub = resultat.getString("datepub");
				String niveau = resultat.getString("niveau");
				Eleve e = new Eleve();
				e.setEleve(idEleve);
				o.setEleve(e);
				o.setCours(cours);
				o.setId(id);
				o.setDatepub(datepub);
				o.setStatus(status);
				o.setNiveau(niveau);
				
				
				Postuler p = new Postuler();
				p.setOffre(o);
				a.add(p);
				
				
				
				

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}

	
	public ArrayList<Postuler> lesProfesseurs(int idEleve) {
		ArrayList<Postuler> a = new ArrayList<Postuler>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre,postuler WHERE offre.id = postuler.id_offre AND id_eleve = "+idEleve+" AND postuler.status = 'accepter' ORDER BY offre.id DESC";
			System.out.println(sql);
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				Offre o = new Offre();
				
				int id = resultat.getInt("id");
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				Professeur professeur = new Professeur();
				String email = resultat.getString("email_enseignant");
				professeur.setProfesseur(email);
				String status = resultat.getString("status");
				String datepub = resultat.getString("datepub");
				String niveau = resultat.getString("niveau");
				
				o.setCours(cours);
				o.setId(id);
				o.setDatepub(datepub);
				o.setStatus(status);
				o.setNiveau(niveau);
				
				
				Postuler p = new Postuler();
				p.setProfesseur(professeur);
				p.setOffre(o);
				a.add(p);
				
				
				
				

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}

}
