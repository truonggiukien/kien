/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entitybeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Caterers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caterers.findAll", query = "SELECT c FROM Caterers c"),
    @NamedQuery(name = "Caterers.findByCatererId", query = "SELECT c FROM Caterers c WHERE c.catererId = :catererId"),
    @NamedQuery(name = "Caterers.findByName", query = "SELECT c FROM Caterers c WHERE c.name = :name"),
    @NamedQuery(name = "Caterers.findByAddress", query = "SELECT c FROM Caterers c WHERE c.address = :address"),
    @NamedQuery(name = "Caterers.findByPhone", query = "SELECT c FROM Caterers c WHERE c.phone = :phone"),
    @NamedQuery(name = "Caterers.findByDateOfJoin", query = "SELECT c FROM Caterers c WHERE c.dateOfJoin = :dateOfJoin")})
public class Caterers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatererId")
    private Integer catererId;
    @Size(max = 2147483647)
    @Column(name = "Name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "Address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "Phone")
    private String phone;
    @Column(name = "DateOfJoin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfJoin;
    @OneToMany(mappedBy = "catererID")
    private Collection<Orders> ordersCollection;

    public Caterers() {
    }

    public Caterers(Integer catererId) {
        this.catererId = catererId;
    }

    public Integer getCatererId() {
        return catererId;
    }

    public void setCatererId(Integer catererId) {
        this.catererId = catererId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catererId != null ? catererId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caterers)) {
            return false;
        }
        Caterers other = (Caterers) object;
        if ((this.catererId == null && other.catererId != null) || (this.catererId != null && !this.catererId.equals(other.catererId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.Caterers[ catererId=" + catererId + " ]";
    }
    
}
