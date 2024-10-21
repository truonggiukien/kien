/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface CustomersFacadeLocal {

    void create(Customers customers);

    void edit(Customers customers);

    void remove(Customers customers);

    Customers find(Object id);

    List<Customers> findAll();

    List<Customers> findRange(int[] range);

    int count();


    public Customers findCustomerbyUsername(String username);

    public Customers findCustomerByEmail(String email);

    public boolean isEmailExists(String email);

    public boolean isUsernameExists(String username);

  
}
