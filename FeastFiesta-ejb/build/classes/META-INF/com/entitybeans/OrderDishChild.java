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
@Table(name = "OrderDishChild")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDishChild.findAll", query = "SELECT o FROM OrderDishChild o"),
    @NamedQuery(name = "OrderDishChild.findByOrderDishID", query = "SELECT o FROM OrderDishChild o WHERE o.orderDishID = :orderDishID"),
    @NamedQuery(name = "OrderDishChild.findByQuantity", query = "SELECT o FROM OrderDishChild o WHERE o.quantity = :quantity")})
public class OrderDishChild implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderDishID")
    private Integer orderDishID;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "DishID", referencedColumnName = "DishID")
    @ManyToOne(optional = false)
    private Dishes dishID;
    @JoinColumn(name = "OrderNo", referencedColumnName = "OrderNo")
    @ManyToOne(optional = false)
    private Orders orderNo;

    public OrderDishChild() {
    }

    public OrderDishChild(Integer orderDishID) {
        this.orderDishID = orderDishID;
    }

    public Integer getOrderDishID() {
        return orderDishID;
    }

    public void setOrderDishID(Integer orderDishID) {
        this.orderDishID = orderDishID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Dishes getDishID() {
        return dishID;
    }

    public void setDishID(Dishes dishID) {
        this.dishID = dishID;
    }

    public Orders getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Orders orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDishID != null ? orderDishID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDishChild)) {
            return false;
        }
        OrderDishChild other = (OrderDishChild) object;
        if ((this.orderDishID == null && other.orderDishID != null) || (this.orderDishID != null && !this.orderDishID.equals(other.orderDishID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.OrderDishChild[ orderDishID=" + orderDishID + " ]";
    }
    
}
