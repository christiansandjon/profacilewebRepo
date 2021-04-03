package com.profacile.model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Professeur")
public class Professeur implements Serializable{

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
	
	@Column(name="password")
	private String password;
	
	@Column(name="montanthoraire")
	private String montant;
	
	@Column(name="description")
	private String description;	
	
	@Column(name="cv")
	private String cv;	
	
	
	public void setMontant(String montant) {
		this.montant = montant;
	}
	
	public String getMontant() {
		return this.montant;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setCv(String cv) {
		this.cv = cv;
	}
	
	public String getCv() {
		return this.cv;
	}
	
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
	
	public String getPassword() {
		
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		
	}

	
	public boolean update(String emailtrue) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:8889/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "UPDATE enseignant SET nom = '"+this.nom+"', prenom = '"+this.prenom+"', adresse = '"+this.adresse+"', ville = '"+this.ville+"', telephone = '"+this.telephone+"', pays = '"+this.pays+"', montanthoraire = '"+this.montant+"', description = '"+this.description+"' WHERE email = '"+emailtrue+"'";
			
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 			
		
	}
	
	
	public void setProfesseur(String email) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM enseignant WHERE email='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.password = resultat.getString("password");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 this.adresse = resultat.getString("adresse");
				 this.ville = resultat.getString("ville");
				 this.pays = resultat.getString("pays");
				 this.telephone = resultat.getString("telephone");
				 this.montant = resultat.getString("montanthoraire");
				 this.cv = resultat.getString("cv");
				 this.description = resultat.getString("description");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
		
	}
	public String getIntitule(int id_cours){
		String intitule="";
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM cours WHERE id="+id_cours+"";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				intitule = resultat.getString("intitule");
				
				
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return intitule;	
	}
	public int[] listeCoursTout(){
		int[] tab = new int[this.nombreTousCours()];
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM cours ";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				tab[i] = resultat.getInt("id");
				
				i++;
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return tab;	
	}
	
	public int[] listeCoursTout2(){
		int[] tab = new int[this.nombreTousCours()];
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM cours ";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				tab[i] = resultat.getInt("id");
				
				i++;
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return tab;	
	}
	
	
	public int nombreCours(String email) {
		
		int nombre =0;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT COUNT(*) AS nombre FROM enseigner WHERE email_enseignant='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				nombre = resultat.getInt("nombre");
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return nombre;		
	}
	
	public int nombreTousCours() {
		
		int nombre =0;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT COUNT(*) AS nombre FROM cours";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				nombre = resultat.getInt("nombre");
				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return nombre;		
	}
	
	

	
	public int[] listeCours(String email) {
		int[] tab = new int[this.nombreCours(email)];
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM enseigner WHERE email_enseignant='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				int result = resultat.getInt("id_cours");
				tab[i] = result;
				i++;
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return tab;
	}
	
	public boolean save() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO enseignant(id,nom,prenom,email,password) VALUES (NULL,'"+this.nom+"','"+this.prenom+"','"+this.email+"','"+this.password+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			} 		
	}
	
	public void dateFromProfesseur(String email) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM enseignant WHERE email='"+email+"'";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.email = resultat.getString("email");
				 this.password = resultat.getString("password");
				 this.nom = resultat.getString("nom");
				 this.prenom = resultat.getString("prenom");
				 this.adresse = resultat.getString("adresse");
				 this.ville = resultat.getString("ville");
				 this.pays = resultat.getString("pays");
				 this.telephone = resultat.getString("telephone");
				 this.montant = resultat.getString("montanthoraire");
				 this.description = resultat.getString("description");
				 this.cv = resultat.getString("cv");
				 
				
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
			String sql = "SELECT * FROM enseignant";
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
	
	public boolean isFreeEmail() {
		boolean test = true;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM enseignant";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				String user = resultat.getString("email");
				
				if((user.equals(this.email))){
					test = false;
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
			String sql = "SELECT * FROM enseignant WHERE id ="+id+"";
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
	
	public void updateMontant(String montant) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE enseignant SET montanthoraire = '"+montant+"' WHERE email='"+this.email+"'";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 				
		
	}
	
	public void updateDescription(String description) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE enseignant SET description = '"+description+"' WHERE email='"+this.email+"'";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 				
		
	}
	
	public void update(int id,String password) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "UPDATE enseignant SET password = '"+password+"' WHERE id="+id+"";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 				
		
	}
	
	public boolean saveMatiere(String email,Integer id_matiere) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO enseigner(id_cours,email_enseignant) VALUES ("+id_matiere+",'"+email+"')";
			stmt.executeUpdate(sql);  
			return true;
			}catch(Exception e){ System.out.println(e);
			return false;
			}		
		
	}
	
	
	
}
