/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import com.entitybeans.Ingredients;
import com.sessionbeans.DishIngChildFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.IngredientsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Huynh
 */
@Named(value = "dishIngChildMB")
@RequestScoped
public class DishIngChildMB {

    @EJB
    private DishesFacadeLocal dishesFacade;

    @EJB
    private IngredientsFacadeLocal ingredientsFacade;

    @EJB
    private DishIngChildFacadeLocal dishIngChildFacade;
    private DishIngChild dishing;

    private int dishID;
    private int ingredientID;

    public DishIngChildMB() {
        dishing = new DishIngChild();
    }

    public String createDishIng() {
        dishing.setDishID(dishesFacade.find(this.dishID));
        dishing.setIngredientID(ingredientsFacade.find(this.ingredientID));
        dishIngChildFacade.create(dishing);
        return "dishes_view";
    }

    public List<Ingredients> showAllIngredient() {
        return ingredientsFacade.findAll();
    }
     public List<Dishes> showAllDishes() {
        return dishesFacade.findAll();
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public DishIngChild getDishing() {
        return dishing;
    }

    public void setDishing(DishIngChild dishing) {
        this.dishing = dishing;
    }

}
