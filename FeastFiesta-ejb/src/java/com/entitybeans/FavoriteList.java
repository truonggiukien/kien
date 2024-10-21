/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entitybeans;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "FavoriteList")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoriteList.findAll", query = "SELECT f FROM FavoriteList f"),
    @NamedQuery(name = "FavoriteList.findByFavoriteID", query = "SELECT f FROM FavoriteList f WHERE f.favoriteID = :favoriteID")})
public class FavoriteList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FavoriteID")
    private Integer favoriteID;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @ManyToOne
    private Customers customerID;
    @JoinColumn(name = "DishID", referencedColumnName = "DishID")
    @ManyToOne
    private Dishes dishID;

    public FavoriteList() {
    }

    public FavoriteList(Integer favoriteID) {
        this.favoriteID = favoriteID;
    }

    public Integer getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(Integer favoriteID) {
        this.favoriteID = favoriteID;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public Dishes getDishID() {
        return dishID;
    }

    public void setDishID(Dishes dishID) {
        this.dishID = dishID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoriteID != null ? favoriteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoriteList)) {
            return false;
        }
        FavoriteList other = (FavoriteList) object;
        if ((this.favoriteID == null && other.favoriteID != null) || (this.favoriteID != null && !this.favoriteID.equals(other.favoriteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.FavoriteList[ favoriteID=" + favoriteID + " ]";
    }
    
}
