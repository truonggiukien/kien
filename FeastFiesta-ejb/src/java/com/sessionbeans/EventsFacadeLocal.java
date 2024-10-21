/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Events;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface EventsFacadeLocal {

    void create(Events events);

    void edit(Events events);

    void remove(Events events);

    Events find(Object id);

    List<Events> findAll();

    List<Events> findRange(int[] range);

    int count();

    public List<Events> findByName(String name);

    public Events findEventByVoucherCode(String voucherCode);
    
}
