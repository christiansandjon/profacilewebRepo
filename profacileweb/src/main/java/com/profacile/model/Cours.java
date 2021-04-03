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
	@Column(name="cours_id")
	private Integer id;
	
	@Column(name="intitule")
	private String intitule;

	private static final long
	serialVersionUID = 1L;

	public static final String connect = "com.mysql.jdbc.Driver";
	public static final String udbc = "jdbc:mysql://localhost:3306/profacile";
	public static final String user = "root";
	public static final String pass = "root";
	
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
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM Cours WHERE id="+id+"";
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
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT COUNT(*) AS nombre FROM Cours";
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
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM Cours ";
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
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);
			 
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