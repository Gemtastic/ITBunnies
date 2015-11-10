/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.entities.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@Entity
@Table(name = "webshop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Webshop.findAll", query = "SELECT w FROM Webshop w"),
    @NamedQuery(name = "Webshop.findById", query = "SELECT w FROM Webshop w WHERE w.id = :id"),
    @NamedQuery(name = "Webshop.findByLogin", query = "SELECT w FROM Webshop w WHERE w.login = :login"),
    @NamedQuery(name = "Webshop.findByPaymentsys", query = "SELECT w FROM Webshop w WHERE w.paymentsys = :paymentsys"),
    @NamedQuery(name = "Webshop.findByDatabase", query = "SELECT w FROM Webshop w WHERE w.database = :database"),
    @NamedQuery(name = "Webshop.findByPrice", query = "SELECT w FROM Webshop w WHERE w.price = :price"),
    @NamedQuery(name = "Webshop.findByTimeest", query = "SELECT w FROM Webshop w WHERE w.timeest = :timeest")})
public class Webshop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "login")
    private boolean login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "paymentsys")
    private String paymentsys;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "database")
    private String database;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeest")
    private int timeest;
    @JoinColumn(name = "language", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "quote", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quote quote;

    public Webshop() {
    }

    public Webshop(Integer id) {
        this.id = id;
    }

    public Webshop(Integer id, boolean login, String paymentsys, String database, int price, int timeest) {
        this.id = id;
        this.login = login;
        this.paymentsys = paymentsys;
        this.database = database;
        this.price = price;
        this.timeest = timeest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getPaymentsys() {
        return paymentsys;
    }

    public void setPaymentsys(String paymentsys) {
        this.paymentsys = paymentsys;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTimeest() {
        return timeest;
    }

    public void setTimeest(int timeest) {
        this.timeest = timeest;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Webshop)) {
            return false;
        }
        Webshop other = (Webshop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gemtastic.model.entities.database.Webshop[ id=" + id + " ]";
    }
    
}
