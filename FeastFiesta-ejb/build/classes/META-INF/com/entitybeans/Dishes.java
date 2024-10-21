/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entitybeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Dishes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dishes.findAll", query = "SELECT d FROM Dishes d"),
    @NamedQuery(name = "Dishes.findByDishID", query = "SELECT d FROM Dishes d WHERE d.dishID = :dishID"),
    @NamedQuery(name = "Dishes.findByItemName", query = "SELECT d FROM Dishes d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "Dishes.findByPrice", query = "SELECT d FROM Dishes d WHERE d.price = :price"),
    @NamedQuery(name = "Dishes.findByPicture", query = "SELECT d FROM Dishes d WHERE d.picture = :picture")})
public class Dishes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DishID")
    private Integer dishID;
    @Size(max = 2147483647)
    @Column(name = "ItemName")
    private String itemName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @Size(max = 2147483647)
    @Column(name = "Picture")
    private String picture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishID")
    private Collection<MenuDishChild> menuDishChildCollection;
    @OneToMany(mappedBy = "dishID")
    private Collection<FavoriteList> favoriteListCollection;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne
    private Categories categoryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishID")
    private Collection<OrderDishChild> orderDishChildCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishID")
    private Collection<DishIngChild> dishIngChildCollection;

    public Dishes() {
    }

    public Dishes(Integer dishID) {
        this.dishID = dishID;
    }

    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<MenuDishChild> getMenuDishChildCollection() {
        return menuDishChildCollection;
    }

    public void setMenuDishChildCollection(Collection<MenuDishChild> menuDishChildCollection) {
        this.menuDishChildCollection = menuDishChildCollection;
    }

    @XmlTransient
    public Collection<FavoriteList> getFavoriteListCollection() {
        return favoriteListCollection;
    }

    public void setFavoriteListCollection(Collection<FavoriteList> favoriteListCollection) {
        this.favoriteListCollection = favoriteListCollection;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    @XmlTransient
    public Collection<OrderDishChild> getOrderDishChildCollection() {
        return orderDishChildCollection;
    }

    public void setOrderDishChildCollection(Collection<OrderDishChild> orderDishChildCollection) {
        this.orderDishChildCollection = orderDishChildCollection;
    }

    @XmlTransient
    public Collection<DishIngChild> getDishIngChildCollection() {
        return dishIngChildCollection;
    }

    public void setDishIngChildCollection(Collection<DishIngChild> dishIngChildCollection) {
        this.dishIngChildCollection = dishIngChildCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dishID != null ? dishID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dishes)) {
            return false;
        }
        Dishes other = (Dishes) object;
        if ((this.dishID == null && other.dishID != null) || (this.dishID != null && !this.dishID.equals(other.dishID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.Dishes[ dishID=" + dishID + " ]";
    }
    
}
