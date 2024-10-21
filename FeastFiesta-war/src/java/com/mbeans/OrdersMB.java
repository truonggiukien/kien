/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Caterers;
import com.entitybeans.Customers;
import com.entitybeans.OrderDishChild;
import com.entitybeans.Orders;
import com.sessionbeans.CaterersFacadeLocal;
import com.sessionbeans.CustomersFacadeLocal;
import com.sessionbeans.EventsFacadeLocal;
import com.sessionbeans.OrderDishChildFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh
 */
@Named(value = "ordersMB")
@RequestScoped
public class OrdersMB {

    @EJB
    private OrderDishChildFacadeLocal orderDishChildFacade;

    @EJB
    private CaterersFacadeLocal caterersFacade;

    @EJB
    private EventsFacadeLocal eventsFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    private String discount;
    private Orders order;
    private int customerID;
    private int eventID;
    private int catererID;
    private Date currentDate;
    private String deliveryDate;
    private int month;
    private int year = 2024;

    public List<Customers> showAllCustomers() {
        return customersFacade.findAll();
    }

    public OrdersMB() {
        order = new Orders();
    }

    public String addOrder() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        currentDate = new Date();
        order.setOrderDate(currentDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse(deliveryDate);
        order.setDeliveryDate(parsedDate);
        if (order.getNotes() == null || order.getNotes().trim().isEmpty()) {
            order.setNotes("None");
        }
        order.setCatererID(null);
        order.setTotalPrice(null);
        order.setEventID(null);
        order.setStatus("Pending");
        String username = (String) session.getAttribute("username");
        order.setName(username);
        order.setCustomerID(customersFacade.find(session.getAttribute("customerID")));
        ordersFacade.create(order);
        return "home";
    }

    public List<Caterers> showAllCaterers() {
        return caterersFacade.findAll();
    }

    public String editOrders() {
        Orders existingOrder = ordersFacade.find(order.getOrderNo());
        if (existingOrder != null) {
            order.setOrderDate(existingOrder.getOrderDate());
            order.setDeliveryDate(existingOrder.getDeliveryDate());
            order.setDeliveryAddress(existingOrder.getDeliveryAddress());
            order.setTotalPrice(existingOrder.getTotalPrice());
            order.setName(existingOrder.getName());
            order.setNotes(existingOrder.getNotes());
            order.setCustomerID(existingOrder.getCustomerID());
            order.setEventID(existingOrder.getEventID());
            order.setCatererID(caterersFacade.find(this.catererID));
            ordersFacade.edit(order);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditOrder:orderID", new FacesMessage("Order ID don't exist"));
            return findOrderForUpdate(order);
        }
        return "orders_view";
    }

    public String findOrderForUpdate(Orders ord) {
        order = ord;
        return "orders_edit";
    }

    public String findOrderDetail(Orders ord) {
        order = ord;
        return "orders_detail";
    }
     public String findOrderDetailCus(Orders ord) {
        order = ord;
        return "detailorder";
    }

    public String deleteOrder(Orders ord) {
        ordersFacade.remove(ord);
        return "orders_view";
    }

    public BigDecimal TotalMonthPrice() {
        List<Orders> orders = ordersFacade.findOrdersByMonthYearAndStatus(month, year, "Success");
        BigDecimal totalMonthPrice = BigDecimal.ZERO;
        for (Orders order : orders) {
            if(order.getTotalPrice() != null){
                totalMonthPrice = totalMonthPrice.add(order.getTotalPrice());
            }
        }
        
       return totalMonthPrice;
    }

    public int totalOrder() {
        int totalO = ordersFacade.count();
        return totalO;
    }

    public int totalOrderByStatus(String status) {
        int totalbyStatus = ordersFacade.findOrdersByStatus(status).size();
        return totalbyStatus;
    }
    private BigDecimal totalOrderPrice;

    public BigDecimal getTotalOrderPrice() {
        if (totalOrderPrice == null) {
            calculateTotalOrderPrice();
        }
        return totalOrderPrice;
    }

   private void calculateTotalOrderPrice() {
    List<Orders> orders = ordersFacade.findAll();
    totalOrderPrice = BigDecimal.ZERO;
    for (Orders order : orders) {
        if (order.getTotalPrice() != null) {
            totalOrderPrice = totalOrderPrice.add(order.getTotalPrice());
        }
    }
}

    public List<OrderDishChild> showDishOrder() {
        return orderDishChildFacade.findOrderDishByOrderID(order);
    }

    public String resetForm() {
        order = null;
        return "orders_view";
    }

    public List<Orders> showOrderByDate() {

        return ordersFacade.findOrdersByMonthAndYear(month, year);
    }

    public List<Orders> showAllOrders() {
        return ordersFacade.findAll();
    }

    public List<Orders> showAllOrdersbyCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        return ordersFacade.findOrdersByCustomerID(customersFacade.find(session.getAttribute("customerID")));
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCatererID() {
        return catererID;
    }

    public void setCatererID(int catererID) {
        this.catererID = catererID;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

}
