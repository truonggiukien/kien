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
@Table(name = "Events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
    @NamedQuery(name = "Events.findByEventID", query = "SELECT e FROM Events e WHERE e.eventID = :eventID"),
    @NamedQuery(name = "Events.findByEventName", query = "SELECT e FROM Events e WHERE e.eventName = :eventName"),
    @NamedQuery(name = "Events.findByBeginDate", query = "SELECT e FROM Events e WHERE e.beginDate = :beginDate"),
    @NamedQuery(name = "Events.findByEndDate", query = "SELECT e FROM Events e WHERE e.endDate = :endDate"),
    @NamedQuery(name = "Events.findByQuantity", query = "SELECT e FROM Events e WHERE e.quantity = :quantity"),
    @NamedQuery(name = "Events.findByDiscount", query = "SELECT e FROM Events e WHERE e.discount = :discount")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EventID")
    private Integer eventID;

    @Size(max = 2147483647)
    @Column(name = "EventName")
    private String eventName;

    @Column(name = "BeginDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;

    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "Quantity")
    private Integer quantity;

    @Size(max = 2147483647)
    @Column(name = "Discount")
    private String discount;

    @Size(max = 2147483647)
    @Column(name = "image_path")  // New column to store image path
    private String imagePath;

    @OneToMany(mappedBy = "eventID")
    private Collection<Orders> ordersCollection;

    // Constructors, getters, and setters

    public Events() {
    }

    public Events(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        return !((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID)));
    }

    @Override
    public String toString() {
        return "com.entitybeans.Events[ eventID=" + eventID + " ]";
    }
}
