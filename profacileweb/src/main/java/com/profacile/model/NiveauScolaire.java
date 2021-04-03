package com.profacile.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Entity
@Table(name="NiveauScolaire")

public class NiveauScolaire implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NivSco_id")
	private Integer id;

	@Column(name="niveau_scolaire")
	private String niveau_scolaire;

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
	public String getNiveau_scolaire() {
		return this.niveau_scolaire;
	}
	public void setNiveau_scolaire(String intitule) {
		this.niveau_scolaire = niveau_scolaire;
	}

	public void setNiveauScolaire(int id) {
		try{
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);

			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM NiveauScolaire WHERE id="+id+"";
			ResultSet resultat = stmt.executeQuery(sql);
			while(resultat.next()) {
				this.id = resultat.getInt("NivSco_id");
				 this.niveau_scolaire = resultat.getString("niveau_scolaire");


			}

			}catch(Exception e){ System.out.println(e);

			}

	}


	public int nombreTousNiveauScolaire() {

		int nombre =0;

		try{
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);

			Statement stmt=con.createStatement();
			String sql = "SELECT COUNT(*) AS nombre FROM NiveauScolaire";
			ResultSet resultat = stmt.executeQuery(sql);
			while(resultat.next()) {
				nombre = resultat.getInt("nombre");

			}

			}catch(Exception e){ System.out.println(e);

			}
		return nombre;
	}

	public String getNiveau_scolaire(int id_niveauScolaire){
		String niveau_scolaire="";
		int i =0;
		try{
			Class.forName(connect);
			Connection con=DriverManager.getConnection(udbc,user,pass);

			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM niveauScolaire WHERE id="+id_niveauScolaire+"";
			ResultSet resultat = stmt.executeQuery(sql);
			while(resultat.next()) {
				niveau_scolaire = resultat.getString("niveau_scolaire");

			}
			
			}catch(Exception e){ System.out.println(e);
		
			}		
		return niveau_scolaire;
	}
	
}