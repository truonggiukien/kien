/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Orders;
import com.entitybeans.Dishes;
import com.entitybeans.Events;
import com.entitybeans.FavoriteList;
import com.entitybeans.Menu;
import com.entitybeans.MenuDishChild;
import com.entitybeans.OrderDishChild;
import com.sessiobeansprocess.CartSBLocal;
import com.sessionbeans.CustomersFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.EventsFacadeLocal;
import com.sessionbeans.FavoriteListFacadeLocal;
import com.sessionbeans.MenuDishChildFacadeLocal;
import com.sessionbeans.MenuFacadeLocal;
import com.sessionbeans.OrderDishChildFacadeLocal;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@Named(value = "cartProcessSBMB")
@RequestScoped
public class CartProcessSBMB {

    @EJB
    private EventsFacadeLocal eventsFacade;

    @EJB
    private MenuDishChildFacadeLocal menuDishChildFacade;

    @EJB
    private MenuFacadeLocal menuFacade;

    @EJB
    private OrderDishChildFacadeLocal orderDishChildFacade;

    private int quantity = 1;
    @EJB
    private CartSBLocal cartSB;
    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private DishesFacadeLocal dishsFacade;
    @EJB
    private OrdersFacadeLocal ordersFacade;
    private Orders order;
    private int customerID;
    private double totalPrice;
    private String deliveryDate;
    private Date currentDate;
    private String voucherCode;
    private double discount = 0.0;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public CartProcessSBMB() {
        order = new Orders();
    }

    public Map<Dishes, Integer> showAllCart() {
        return cartSB.getCartMap();
    }

    public String checkout() {

        return "checkout";
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
        double temp = this.getTotalPriceS();
        BigDecimal bigDecimalValue = BigDecimal.valueOf(temp);
        order.setTotalPrice(bigDecimalValue);
        order.setCatererID(null);
        order.setStatus("Pending");
        String username = (String) session.getAttribute("username");
        order.setName(username);
        String tempdiscount = (String) session.getAttribute("code");
        if (tempdiscount != null) {
            
            Events event = eventsFacade.findEventByVoucherCode(tempdiscount);
            order.setEventID(event);
         
            
            if (event != null) {
                event.setQuantity(event.getQuantity() - 1);
                eventsFacade.edit(event);
            }
        } else {
            order.setEventID(null);
        }
        order.setCustomerID(customersFacade.find(session.getAttribute("customerID")));
        ordersFacade.create(order);

        //create orderdish
        for (Map.Entry<Dishes, Integer> entry : cartSB.getCartMap().entrySet()) {
            Dishes dish = entry.getKey();
            Integer orderquantity = entry.getValue();
            OrderDishChild orderDishChild = new OrderDishChild();
            orderDishChild.setDishID(dishsFacade.find(dish.getDishID())); // Set the DishID
            orderDishChild.setOrderNo(ordersFacade.find(order.getOrderNo())); // Set the OrderID obtained above
            orderDishChild.setQuantity(orderquantity);

            // Save the entry in OrderDishChild table
            orderDishChildFacade.create(orderDishChild);
        }

        // Clear the shopping cart
        cartSB.clearnCart();
        return "listorders";
    }

    public String getFormattedDiscount() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Object discountsession = session.getAttribute("discount");
        if (discountsession != null) {
            double discountT = (Double) discountsession;
            int discountPercentage = (int) (discountT);
            return discountPercentage + "%";
        } else {
            return null;
        }

    }

    public String addCart(int idPro) {
        Dishes pro = dishsFacade.find(idPro);

        // Check if the product is already in the cart
        if (cartSB.getCartMap().containsKey(pro)) {
            // Product is already in the cart, increase the quantity
            int currentQuantity = cartSB.getCartMap().get(pro);
            cartSB.increaseCart(pro, currentQuantity + 1);
        } else {
            // Product is not in the cart, add a new item
            cartSB.addCart(pro, quantity);
        }

        return "home";
    }
     

    // Getter and Setter for voucherCode
    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void applyVoucher() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        // Clear current discount session
        session.removeAttribute("discount");

        Events event = eventsFacade.findEventByVoucherCode(voucherCode);
        
        
        Date now = new Date();
        boolean isPire = !now.before(event.getBeginDate()) && !now.after(event.getEndDate());
        
  

        if (event != null && event.getDiscount() != null &&  event.getQuantity() >= 1 && isPire ) { 
            try {
                discount = Double.parseDouble(event.getDiscount());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Voucher applied!"));
                session.setAttribute("discount", discount);
                session.setAttribute("code", voucherCode);
            } catch (NumberFormatException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid discount format", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid voucher code", null));
        }
    }

    public void clearVoucher() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.removeAttribute("discount");
    }

    public double getTotalPriceS() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if (session.getAttribute("discount") != null) {
            double temp = (double) session.getAttribute("discount");
            double total = 0.0;
            for (Map.Entry<Dishes, Integer> entry : cartSB.getCartMap().entrySet()) {
                total += entry.getKey().getPrice().doubleValue() * entry.getValue().doubleValue();
            }
            return total * (1 - temp / 100);
        } else {
            double total = 0.0;
            for (Map.Entry<Dishes, Integer> entry : cartSB.getCartMap().entrySet()) {
                total += entry.getKey().getPrice().doubleValue() * entry.getValue().doubleValue();
            }
            return total * (1 - discount / 100);
        }

    }

    public String addCartMenu(Menu menu) {
        List<MenuDishChild> _menudish = menuDishChildFacade.findDishesByMenuID(menu);

        for (MenuDishChild menuDishChild : _menudish) {
            Dishes _dish = menuDishChild.getDishID();

            int _quantity = menuDishChild.getQuantity();

            if (cartSB.getCartMap().containsKey(_dish)) {
                // Product is already in the cart, increase the quantity
                int currentQuantity = cartSB.getCartMap().get(_dish);
                cartSB.increaseCart(_dish, currentQuantity + _quantity);
            } else {
                // Product is not in the cart, add a new item
                cartSB.addCart(_dish, _quantity);
            }
        }

        return "home";
    }

    public String gotoCart() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.removeAttribute("discount");
        return "cart";
    }

    public String increaseCart(int idPro, int newquantity) {
        Dishes pro = dishsFacade.find(idPro);
        cartSB.increaseCart(pro, newquantity);
        return "cart";
    }

    public String decreaseCart(int idPro, int newquantity) {
        Dishes pro = dishsFacade.find(idPro);
        cartSB.decreaseCart(pro, newquantity);
        return "cart";
    }

    public double sumCart() {
        double sum = 0;
        for (Map.Entry<Dishes, Integer> e : cartSB.getCartMap().entrySet()) {
            sum += e.getKey().getPrice().doubleValue() * e.getValue().doubleValue();
        }
        return sum;
    }

    public int sumProductCart() {
        int sum = 0;
        for (Map.Entry<Dishes, Integer> e : cartSB.getCartMap().entrySet()) {
            sum += e.getValue();
        }
        return sum;
    }

    public int countUniqueProductsInCart() {
        return cartSB.getCartMap().size();
    }

    public String removeItemCart(int idPro) {
        Dishes pro = dishsFacade.find(idPro);
        cartSB.removeItemCart(pro);
        return "cart";
    }

    public String clearnCart() {
        cartSB.clearnCart();
        quantity = 1;
        return "cart";
    }

//getter - setter
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
