/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Dishes;
import com.entitybeans.OrderDishChild;
import com.entitybeans.Orders;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface OrderDishChildFacadeLocal {

    void create(OrderDishChild orderDishChild);

    void edit(OrderDishChild orderDishChild);

    void remove(OrderDishChild orderDishChild);

    OrderDishChild find(Object id);

    List<OrderDishChild> findAll();

    List<OrderDishChild> findRange(int[] range);

    int count();

    public List<OrderDishChild> findOrderDishByOrderID(Orders orderNo);


    public List<Dishes> recommendDishesByDishID(Dishes dishID);


    
}
