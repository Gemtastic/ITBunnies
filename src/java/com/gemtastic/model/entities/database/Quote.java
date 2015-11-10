/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.entities.database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gemtastic
 */
@Entity
@Table(name = "quote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quote.findAll", query = "SELECT q FROM Quote q"),
    @NamedQuery(name = "Quote.findById", query = "SELECT q FROM Quote q WHERE q.id = :id"),
    @NamedQuery(name = "Quote.findByQuerydate", query = "SELECT q FROM Quote q WHERE q.querydate = :querydate"),
    @NamedQuery(name = "Quote.findByQuotesum", query = "SELECT q FROM Quote q WHERE q.quotesum = :quotesum")})
public class Quote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "querydate")
    @Temporal(TemporalType.DATE)
    private Date querydate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quotesum")
    private int quotesum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    private List<Website> websiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    private List<Webshop> webshopList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    private List<Graphic> graphicList;

    public Quote() {
    }

    public Quote(Integer id) {
        this.id = id;
    }

    public Quote(Integer id, Date querydate, int quotesum) {
        this.id = id;
        this.querydate = querydate;
        this.quotesum = quotesum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getQuerydate() {
        return querydate;
    }

    public void setQuerydate(Date querydate) {
        this.querydate = querydate;
    }

    public int getQuotesum() {
        return quotesum;
    }

    public void setQuotesum(int quotesum) {
        this.quotesum = quotesum;
    }

    @XmlTransient
    public List<Website> getWebsiteList() {
        return websiteList;
    }

    public void setWebsiteList(List<Website> websiteList) {
        this.websiteList = websiteList;
    }

    @XmlTransient
    public List<Webshop> getWebshopList() {
        return webshopList;
    }

    public void setWebshopList(List<Webshop> webshopList) {
        this.webshopList = webshopList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @XmlTransient
    public List<Graphic> getGraphicList() {
        return graphicList;
    }

    public void setGraphicList(List<Graphic> graphicList) {
        this.graphicList = graphicList;
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
        if (!(object instanceof Quote)) {
            return false;
        }
        Quote other = (Quote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gemtastic.model.entities.database.Quote[ id=" + id + " ]";
    }
    
}
