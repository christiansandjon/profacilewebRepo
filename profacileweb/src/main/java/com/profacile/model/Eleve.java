package com.profacile.model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Eleve")
public class Eleve implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="eleve_id")
	private Integer id;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="nom")
	private String nom;

	@Column(name="email")
	private String email;

	@Column(name="telephone")
	private String telephone;

	@Column(name="password")
	private String password;
	
	@Column(name="fkAdresse")
	private Adresse adresse;

	@Column(name="niveauScolaire")
	private NiveauScolaire niveauScolaire;

	/*
	@Column(name="pays")
	private String pays;
	
	@Column(name="ville")
	private String ville;
*/


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public NiveauScolaire getNiveauScolaire() {
		return niveauScolaire;
	}

	public void setNiveauScolaire(NiveauScolaire niveauScolaire) {
		this.niveauScolaire = niveauScolaire;
	}

	public boolean update(String emailtrue) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:8889/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			//String sql = "UPDATE eleve SET nom = '"+this.nom+"', prenom = '"+this.prenom+"', adresse = '"+this.adresse+"', niveauScolaire = '"+this.niveauScolaire+"', ville = '"+this.ville+"', telephone = '"+this.telephone+"', pays = '"+this.pays+"' WHERE email = '"+emailtrue+"'";
			//stmt.executeUpdate(sql);
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 			
		
	}
	
	public boolean save() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:8889/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO eleve(id,nom,prenom,email,niveauScolaire,password) VALUES (NULL,'"+this.nom+"','"+this.prenom+"','"+this.email+"','"+this.niveauScolaire+"','"+this.password+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 		
	}
	public void setEleve(int ideleve) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve WHERE id='"+ideleve+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.password = resultat.getString("password");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 //this.adresse = resultat.getString("adresse");
				 //this.ville = resultat.getString("ville");
				 //this.pays = resultat.getString("pays");
				 //this.niveauScolaire = resultat.getString("niveauScolaire");
				 this.telephone = resultat.getString("telephone");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
		
	}
	public void dateFromEleve(String email) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve WHERE email='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.password = resultat.getString("password");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 //this.adresse = resultat.getString("adresse");
				 //this.ville = resultat.getString("ville");
				 //this.pays = resultat.getString("pays");
				 //this.niveauScolaire = resultat.getString("niveauScolaire");
				 this.telephone = resultat.getString("telephone");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
		
	}
	
	public boolean isUser() {
		
		boolean test = false;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				String user = resultat.getString("email");
				String pass = resultat.getString("password");
				
				if((user.equals(this.email))&&(pass.equals(this.password))) {
					test = true;
				}
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			} 			
		return test;
	}
	
	public String getPassword(int id) {
		String pass="";
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM admin WHERE id ="+id+"";
			System.out.println(sql);
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				String user = resultat.getString("username");
				 pass = resultat.getString("password");
			}
			
			}catch(Exception e){ System.out.println(e);
		
			} 	
		
		return pass;
		
	}
	
	public void update(int id,String password) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE admin SET password = '"+password+"' WHERE id="+id+"";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 				
		
	}
	
	
	
}
