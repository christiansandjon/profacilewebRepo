package com.profacile.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pays")

public class Pays implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pays_id")
    private Integer id;

    @Column(name = "pays_nom")
    private String nom;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}