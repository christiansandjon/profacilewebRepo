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
@Table(name="admin")

public class Admin implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	private static final long
	serialVersionUID = 1L;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void save(String username,String password) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/profacile","root","root");  
			
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO admin(id,username,password) VALUES (NULL,'"+username+"','"+password+"')";
			stmt.executeUpdate(sql);  
			
			}catch(Exception e){ System.out.println(e);
		
			} 		
	}
	
	public boolean isUser() {
		
		boolean test = false;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profacile","root","root");  
			 
			Statement stmt=con.createStatement();  
			String sql = "SELECT * FROM admin";
			ResultSet resultat = stmt.executeQuery(sql);  
			while(resultat.next()) {
				String user = resultat.getString("username");
				String pass = resultat.getString("password");
				
				if((user.equals(this.username))&&(pass.equals(this.password))) {
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
