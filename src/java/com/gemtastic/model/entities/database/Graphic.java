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
@Table(name = "graphic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Graphic.findAll", query = "SELECT g FROM Graphic g"),
    @NamedQuery(name = "Graphic.findById", query = "SELECT g FROM Graphic g WHERE g.id = :id"),
    @NamedQuery(name = "Graphic.findByFormat", query = "SELECT g FROM Graphic g WHERE g.format = :format"),
    @NamedQuery(name = "Graphic.findBySize", query = "SELECT g FROM Graphic g WHERE g.size = :size"),
    @NamedQuery(name = "Graphic.findByPrice", query = "SELECT g FROM Graphic g WHERE g.price = :price"),
    @NamedQuery(name = "Graphic.findByTimeest", query = "SELECT g FROM Graphic g WHERE g.timeest = :timeest")})
public class Graphic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "format")
    private String format;
    @Basic(optional = false)
    @NotNull
    @Column(name = "size")
    private int size;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeest")
    private int timeest;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Graphicstype type;
    @JoinColumn(name = "quote", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quote quote;

    public Graphic() {
    }

    public Graphic(Integer id) {
        this.id = id;
    }

    public Graphic(Integer id, String format, int size, int price, int timeest) {
        this.id = id;
        this.format = format;
        this.size = size;
        this.price = price;
        this.timeest = timeest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public Graphicstype getType() {
        return type;
    }

    public void setType(Graphicstype type) {
        this.type = type;
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
        if (!(object instanceof Graphic)) {
            return false;
        }
        Graphic other = (Graphic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gemtastic.model.entities.database.Graphic[ id=" + id + " ]";
    }
    
}
