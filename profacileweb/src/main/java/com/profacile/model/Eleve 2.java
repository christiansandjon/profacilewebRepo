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
@Table(name="eleve")
public class Eleve implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="pays")
	private String pays;
	
	@Column(name="ville")
	private String ville;
	
	@Column(name="motpasse")
	private String motpasse;
	
	@Column(name="classe")
	private String classe;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		
		return this.id;
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
	
	public String getAdresse() {
		
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
		
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
	
	public void setPays(String pays) {
		this.pays = pays;
		
	}
	
	public String getPays() {
		
		return pays;
	}
	public void setVille(String ville) {
		this.ville = ville;
		
	}
	public String getVille() {
		return ville;
		
	}
	
	public String getMotpasse() {
		
		return motpasse;
	}
	public void setMotpasse(String motpasse) {
		this.motpasse = motpasse;
		
	}
	
	public String getClasse() {
		
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
		
	}
	
	public boolean update(String emailtrue) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "UPDATE eleve SET nom = '"+this.nom+"', prenom = '"+this.prenom+"', adresse = '"+this.adresse+"', classe = '"+this.classe+"', ville = '"+this.ville+"', telephone = '"+this.telephone+"', pays = '"+this.pays+"' WHERE email = '"+emailtrue+"'";
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
			String sql = "INSERT INTO eleve(id,nom,prenom,email,classe,motpasse) VALUES (NULL,'"+this.nom+"','"+this.prenom+"','"+this.email+"','"+this.classe+"','"+this.motpasse+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 		
	}
	public void setEleve(int ideleve) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve WHERE id='"+ideleve+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.motpasse = resultat.getString("motpasse");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 this.adresse = resultat.getString("adresse");
				 this.ville = resultat.getString("ville");
				 this.pays = resultat.getString("pays");
				 this.classe = resultat.getString("classe");
				 this.telephone = resultat.getString("telephone");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
		
	}
	public void dateFromEleve(String email) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve WHERE email='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.motpasse = resultat.getString("motpasse");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 this.adresse = resultat.getString("adresse");
				 this.ville = resultat.getString("ville");
				 this.pays = resultat.getString("pays");
				 this.classe = resultat.getString("classe");
				 this.telephone = resultat.getString("telephone");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
		
	}
	
	public boolean isUser() {
		
		boolean test = false;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM eleve";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				String user = resultat.getString("email");
				String pass = resultat.getString("motpasse");
				
				if((user.equals(this.email))&&(pass.equals(this.motpasse))) {
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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE admin SET password = '"+password+"' WHERE id="+id+"";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 				
		
	}
	
	
	
}
