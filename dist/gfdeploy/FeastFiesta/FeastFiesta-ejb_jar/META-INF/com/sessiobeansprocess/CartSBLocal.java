/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package com.sessiobeansprocess;

import com.entitybeans.Dishes;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface CartSBLocal {

    public void addCart(Dishes dish, int quantity);

    public void increaseCart(Dishes dish, int newquantity);

    public void decreaseCart(Dishes dish, int newquantity);

    public double sumCart();

    public int sumProductCart();

    public String removeItemCart(Dishes dish);

    public String clearnCart();

    public Map<Dishes, Integer> getCartMap();

    
}
