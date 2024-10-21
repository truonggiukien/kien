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
@Table(name = "MenuDishChild")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuDishChild.findAll", query = "SELECT m FROM MenuDishChild m"),
    @NamedQuery(name = "MenuDishChild.findByQuantity", query = "SELECT m FROM MenuDishChild m WHERE m.quantity = :quantity"),
    @NamedQuery(name = "MenuDishChild.findByMenuDishID", query = "SELECT m FROM MenuDishChild m WHERE m.menuDishID = :menuDishID")})
public class MenuDishChild implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Quantity")
    private Integer quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MenuDishID")
    private Integer menuDishID;
    @JoinColumn(name = "DishID", referencedColumnName = "DishID")
    @ManyToOne(optional = false)
    private Dishes dishID;
    @JoinColumn(name = "MenuID", referencedColumnName = "MenuID")
    @ManyToOne(optional = false)
    private Menu menuID;

    public MenuDishChild() {
    }

    public MenuDishChild(Integer menuDishID) {
        this.menuDishID = menuDishID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMenuDishID() {
        return menuDishID;
    }

    public void setMenuDishID(Integer menuDishID) {
        this.menuDishID = menuDishID;
    }

    public Dishes getDishID() {
        return dishID;
    }

    public void setDishID(Dishes dishID) {
        this.dishID = dishID;
    }

    public Menu getMenuID() {
        return menuID;
    }

    public void setMenuID(Menu menuID) {
        this.menuID = menuID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuDishID != null ? menuDishID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuDishChild)) {
            return false;
        }
        MenuDishChild other = (MenuDishChild) object;
        if ((this.menuDishID == null && other.menuDishID != null) || (this.menuDishID != null && !this.menuDishID.equals(other.menuDishID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.MenuDishChild[ menuDishID=" + menuDishID + " ]";
    }
    
}
