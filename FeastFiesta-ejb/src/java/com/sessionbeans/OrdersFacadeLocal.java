/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
import com.entitybeans.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();

    public List<Orders> findOrdersByMonthAndYear(int month, int year);

    public List<Orders> findOrdersByStatus(String stt);

    public List<Orders> findOrdersByMonthYearAndStatus(int month, int year, String status);


    public List<Orders> findOrdersByCustomerID(Customers cus);
    
}
