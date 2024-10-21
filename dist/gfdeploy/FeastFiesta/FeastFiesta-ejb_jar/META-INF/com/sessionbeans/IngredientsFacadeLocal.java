/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import com.entitybeans.Ingredients;
import com.entitybeans.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface IngredientsFacadeLocal {

    void create(Ingredients ingredients);

    void edit(Ingredients ingredients);

    void remove(Ingredients ingredients);

    Ingredients find(Object id);

    List<Ingredients> findAll();

    List<Ingredients> findRange(int[] range);

    int count();

    public List<Ingredients> findIngredientsByOrder(Orders order);

    public List<DishIngChild> findDishIngredientsByOrder(Orders order);

    public List<DishIngChild> findDishIngChildByDishID(Dishes dishID);
    
}
