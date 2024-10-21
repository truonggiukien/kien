/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Categories;
import com.entitybeans.Dishes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface DishesFacadeLocal {

    void create(Dishes dishes);

    void edit(Dishes dishes);

    void remove(Dishes dishes);

    Dishes find(Object id);

    List<Dishes> findAll();

    List<Dishes> findRange(int[] range);

    int count();

    public List<Dishes> findDishesByCategory(Categories categoryId);

    public List<Dishes> findByName(String name);

    public Dishes findDishByDishID(int dishID);


}
