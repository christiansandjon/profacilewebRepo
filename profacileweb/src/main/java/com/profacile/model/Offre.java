package com.profacile.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Entity
@Table(name="offre")

public class Offre implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	private Cours cours;
	private Eleve eleve;
	private String status;
	private String datepub;
	private String datefinoffre;
	private String niveau;
	private static final long
	serialVersionUID = 1L;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStatus() {
		
		return this.status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getDatepub() {
		return this.datepub;
	}
	
	public void setDatepub(String date) {
		this.datepub = date;
	}
	
	public String getDatefinoffre() {
		return this.datefinoffre;
	}
	public void setDatefinoffre(String date) {
		this.datefinoffre = date;
	}
	
	public void setEleve(Eleve eleve) {
		
		this.eleve = eleve;
	}
	
	public Eleve getEleve() {
		return this.eleve;
	}
	
	public Cours getCours() {
		return this.cours;
	}
	
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	public String getNiveau() {
		
		return this.niveau;
	}
	
	public void setNiveau(String niveau) {
		this.niveau = niveau;
		
	}
	
	public String listeOffreProfesseur() {
		String sql = "SELECT * FROM offre WHERE id_cours IN (SELECT id_cours FROM enseigner WHERE email_enseignant = 'frank@gmail.com')";
		return  "";
	}
	
	public boolean postuler(int idoffre,String email) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO postuler(id_offre,email_enseignant) VALUES ("+idoffre+",'"+email+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 	
	}
	
	public boolean save() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO offre(id,id_eleve,id_cours,status,datepub,datefinoffre,niveau) VALUES (NULL,'"+this.eleve.getId()+"','"+this.cours.getId()+"','"+this.status+"','"+this.datepub+"','"+this.datefinoffre+"','"+this.niveau+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 	
	}
	
	public boolean annulerOffre() {
		
		return true;
	}
	
	public boolean isPostuler(int idoffre,String email) {
		boolean test = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM postuler WHERE id_offre="+idoffre+" AND email_enseignant ='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				test= true;
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}			
		return test;
	}
	public boolean updateStatusOffrePostRejeter(int idoffre,String email) {
		
		boolean test = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE postuler SET status='rejeter' WHERE email_enseignant<>'"+email+"' AND id_offre ="+idoffre+"";
			////notification par Email des enseignants rejectés
			stmt.executeUpdate(sql);  
			
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 	
	}
	public boolean updateStatusOffrePostAccepter(int idoffre,String email) {
		
		boolean test = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE postuler SET status='accepter' WHERE email_enseignant='"+email+"' AND id_offre ="+idoffre+"";
		////notification par Email de l'enseignant  accepté
			
			stmt.executeUpdate(sql);  
			
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 	
	}
	public boolean validerOffre(int idoffre,String email) {
		boolean test = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE offre SET status = 'terminer' WHERE id ="+idoffre;
			stmt.executeUpdate(sql);  
			
			updateStatusOffrePostAccepter(idoffre,email);
			updateStatusOffrePostRejeter(idoffre,email);
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 		
	}
	
	public void setOffre() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre WHERE id="+this.id+"";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				int id = resultat.getInt("id");
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				 this.status = resultat.getString("status");
				 this.datepub = resultat.getString("datepub");
				this.niveau = resultat.getString("niveau");
				this.cours = cours;
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}			
		
	}
	
	public ArrayList<Professeur> professeurOffre(int idoffre) {
		ArrayList<Professeur> a = new ArrayList<Professeur>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM postuler WHERE postuler.id_offre="+idoffre+"";
			
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				
				int id = resultat.getInt("id_offre");
				String email = resultat.getString("email_enseignant");
				
				Professeur c = new Professeur();
				c.setProfesseur(email);
				a.add(c);
		
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}
	
	
	public ArrayList<Offre> lesProfesseurs(int idEleve) {
		ArrayList<Offre> a = new ArrayList<Offre>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre,postuler WHERE offre.id = postuler.id_offre AND id_eleve = "+idEleve+" AND postuler.status = 'accepter'";
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
				//o.setProfesseur(professeur);
				a.add(o);
				
				
				
				

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}
	
	public ArrayList<Offre> listeOffre(int idEleve) {
		ArrayList<Offre> a = new ArrayList<Offre>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre WHERE id_eleve="+idEleve+" AND status = 'en cours' ORDER BY id DESC";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				Offre o = new Offre();
				
				int id = resultat.getInt("id");
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				Eleve eleve = new Eleve();
				int ideleve = resultat.getInt("id_eleve");
				eleve.setEleve(ideleve);
				String status = resultat.getString("status");
				String datepub = resultat.getString("datepub");
				String niveau = resultat.getString("niveau");
				
				
				o.setCours(cours);
				o.setId(id);
				o.setDatepub(datepub);
				o.setStatus(status);
				o.setNiveau(niveau);
				o.setEleve(eleve);
				a.add(o);
				
				
				
				

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}
	
	public ArrayList<Offre> listeOffreProfesseurPostuler(String email) {
		ArrayList<Offre> a = new ArrayList<Offre>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre WHERE id_cours IN(SELECT id_cours FROM enseigner WHERE email_enseignant = '"+email+"') ORDER BY id DESC";
			
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				
				int id = resultat.getInt("id");
				if(isPostuler(id,email)==true){
				Offre o = new Offre();
				
				
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				String status = resultat.getString("status");
				String datepub = resultat.getString("datepub");
				String niveau = resultat.getString("niveau");
				Eleve eleve = new Eleve();
				int ideleve = resultat.getInt("id_eleve");
				eleve.setEleve(ideleve);
				
				o.setCours(cours);
				o.setId(id);
				o.setDatepub(datepub);
				o.setStatus(status);
				o.setNiveau(niveau);
				o.setEleve(eleve);
				a.add(o);
				
				
				
				

				}	
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}
	
	


	public ArrayList<Offre> listeOffreProfesseur(String email) {
		ArrayList<Offre> a = new ArrayList<Offre>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM offre WHERE id_cours IN(SELECT id_cours FROM enseigner WHERE email_enseignant = '"+email+"') ORDER BY id DESC";
			
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				
				int id = resultat.getInt("id");
				if(isPostuler(id,email)==false){
				Offre o = new Offre();
				
				
				int idcours = resultat.getInt("id_cours");
				Cours cours = new Cours();
				cours.setCours(idcours);
				String status = resultat.getString("status");
				String datepub = resultat.getString("datepub");
				String niveau = resultat.getString("niveau");
				Eleve eleve = new Eleve();
				int ideleve = resultat.getInt("id_eleve");
				eleve.setEleve(ideleve);
				
				o.setCours(cours);
				o.setId(id);
				o.setDatepub(datepub);
				o.setStatus(status);
				o.setNiveau(niveau);
				o.setEleve(eleve);
				
				a.add(o);
				
				
				
				

				}	
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}	
		return a;
		
	}
	

}