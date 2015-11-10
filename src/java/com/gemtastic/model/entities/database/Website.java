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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@Entity
@Table(name = "website")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Website.findAll", query = "SELECT w FROM Website w"),
    @NamedQuery(name = "Website.findById", query = "SELECT w FROM Website w WHERE w.id = :id"),
    @NamedQuery(name = "Website.findByPages", query = "SELECT w FROM Website w WHERE w.pages = :pages"),
    @NamedQuery(name = "Website.findByPrice", query = "SELECT w FROM Website w WHERE w.price = :price"),
    @NamedQuery(name = "Website.findByTimeest", query = "SELECT w FROM Website w WHERE w.timeest = :timeest")})
public class Website implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pages")
    private int pages;
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
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Websitetype type;

    public Website() {
    }

    public Website(Integer id) {
        this.id = id;
    }

    public Website(Integer id, int pages, int price, int timeest) {
        this.id = id;
        this.pages = pages;
        this.price = price;
        this.timeest = timeest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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

    public Websitetype getType() {
        return type;
    }

    public void setType(Websitetype type) {
        this.type = type;
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
        if (!(object instanceof Website)) {
            return false;
        }
        Website other = (Website) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gemtastic.model.entities.database.Website[ id=" + id + " ]";
    }
    
}
