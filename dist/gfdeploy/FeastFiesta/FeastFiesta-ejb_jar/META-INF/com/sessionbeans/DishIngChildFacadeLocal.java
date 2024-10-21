/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface DishIngChildFacadeLocal {

    void create(DishIngChild dishIngChild);

    void edit(DishIngChild dishIngChild);

    void remove(DishIngChild dishIngChild);

    DishIngChild find(Object id);

    List<DishIngChild> findAll();

    List<DishIngChild> findRange(int[] range);

    int count();

    public List<DishIngChild> findIngByDishID(Dishes dishID);
    
}
