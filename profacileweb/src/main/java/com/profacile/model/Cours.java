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
@Table(name="cours")

public class Cours implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="intitule")
	private String intitule;

	private static final long
	serialVersionUID = 1L;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIntitule() {
		return this.intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public void setCours(int id) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM cours WHERE id="+id+"";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				this.id = resultat.getInt("id");
				 this.intitule = resultat.getString("intitule");

				
			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		
	}
	
	
	public int nombreTousCours() {
		
		int nombre =0;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
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
	
	public int[] listeCoursTout(){
		int[] tab = new int[this.nombreTousCours()];
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
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
	
	public String getIntitule(int id_cours){
		String intitule="";
		int i =0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
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
	
}