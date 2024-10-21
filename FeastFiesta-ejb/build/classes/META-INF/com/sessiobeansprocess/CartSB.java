/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package com.sessiobeansprocess;

import com.entitybeans.Dishes;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;

/**
 *
 * @author Huynh
 */
@Stateful
public class CartSB implements CartSBLocal {

    private int quantity = 1;
    private static Map<Dishes, Integer> cartMap = new HashMap<>();

    @Override
    public void addCart(Dishes dish, int quantity) {
        if (cartMap.containsKey(dish)) {
            cartMap.replace(dish, quantity);
        } else {
            cartMap.put(dish, quantity);
        }
    }

    @Override
    public void increaseCart(Dishes dish, int newquantity) {
        cartMap.replace(dish, newquantity);
    }

    @Override
    public void decreaseCart(Dishes dish, int newquantity) {
        cartMap.replace(dish, newquantity);
    }

    @Override
    public double sumCart() {
        double sum = 0;
        for (Map.Entry<Dishes, Integer> e : cartMap.entrySet()) {
            sum += e.getKey().getPrice().doubleValue() * e.getValue().doubleValue();
        }
        return sum;
    }

    @Override
    public int sumProductCart() {
        int sum = 0;
        for (Map.Entry<Dishes, Integer> e : cartMap.entrySet()) {
            sum += e.getValue();
        }
        return sum;
    }

    @Override
    public String removeItemCart(Dishes dish) {
        cartMap.remove(dish);
        return "cart";
    }

    @Override
    public String clearnCart() {
        cartMap.clear();
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

    @Override
    public Map<Dishes, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Dishes, Integer> cartMap) {
        this.cartMap = cartMap;
    }

}
