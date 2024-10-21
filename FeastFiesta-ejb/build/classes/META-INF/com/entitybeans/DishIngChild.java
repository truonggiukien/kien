/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entitybeans;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DishIngChild")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DishIngChild.findAll", query = "SELECT d FROM DishIngChild d"),
    @NamedQuery(name = "DishIngChild.findByQuantity", query = "SELECT d FROM DishIngChild d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "DishIngChild.findByDishIngID", query = "SELECT d FROM DishIngChild d WHERE d.dishIngID = :dishIngID")})
public class DishIngChild implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Quantity")
    private BigDecimal quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DishIngID")
    private Integer dishIngID;
    @JoinColumn(name = "DishID", referencedColumnName = "DishID")
    @ManyToOne(optional = false)
    private Dishes dishID;
    @JoinColumn(name = "IngredientID", referencedColumnName = "IngredientID")
    @ManyToOne(optional = false)
    private Ingredients ingredientID;

    public DishIngChild() {
    }

    public DishIngChild(Integer dishIngID) {
        this.dishIngID = dishIngID;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getDishIngID() {
        return dishIngID;
    }

    public void setDishIngID(Integer dishIngID) {
        this.dishIngID = dishIngID;
    }

    public Dishes getDishID() {
        return dishID;
    }

    public void setDishID(Dishes dishID) {
        this.dishID = dishID;
    }

    public Ingredients getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Ingredients ingredientID) {
        this.ingredientID = ingredientID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dishIngID != null ? dishIngID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DishIngChild)) {
            return false;
        }
        DishIngChild other = (DishIngChild) object;
        if ((this.dishIngID == null && other.dishIngID != null) || (this.dishIngID != null && !this.dishIngID.equals(other.dishIngID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.DishIngChild[ dishIngID=" + dishIngID + " ]";
    }
    
}
