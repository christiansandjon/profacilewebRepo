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
@Table(name="Competence")

public class Competence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competence_id")
    private Integer id;

    @Column(name = "fkNiveauScolaire")
    private NiveauScolaire niveauScolaire;

    @Column(name = "fkCours")
    private Cours cours;

    private static final long
            serialVersionUID = 1L;

    public static final String connect = "com.mysql.jdbc.Driver";
    public static final String udbc = "jdbc:mysql://localhost:3306/profacile";
    public static final String user = "root";
    public static final String pass = "root";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NiveauScolaire getNiveauScolaire() {
        return niveauScolaire;
    }

    public void setNiveauScolaire(NiveauScolaire niveauScolaire) {
        this.niveauScolaire = niveauScolaire;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}