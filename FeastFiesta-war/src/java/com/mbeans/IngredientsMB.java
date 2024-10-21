/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Ingredients;
import com.entitybeans.Orders;
import com.sessionbeans.IngredientsFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author User
 */
@Named(value = "ingredientsMB")
@RequestScoped
public class IngredientsMB {

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private IngredientsFacadeLocal ingredientsFacade;

    private Ingredients ingredient;
    private Orders order;

    private int OrderNo;

    public IngredientsMB() {
        ingredient = new Ingredients();
        order = new Orders();
    }

    public List<Ingredients> calIngredients() {
        return ingredientsFacade.findIngredientsByOrder(order);
    }

    public List<DishIngChild> calDishIngredients() {
        return ingredientsFacade.findDishIngredientsByOrder(order);
    }

    public String showcalIngredient() {
        order = ordersFacade.find(OrderNo);
        return "ingredients_cal";
    }

    public String addIngredient() {
        ingredientsFacade.create(ingredient);
        return "ingredients_view";
    }

    public List<Orders> showAllOrders() {
        return ordersFacade.findAll();
    }

    public List<Ingredients> showAllIngredients() {
        return ingredientsFacade.findAll();
    }

    public String findIngredientforUpdate(Ingredients dis) {
        ingredient = dis;
        return "ingredients_edit";
    }

    public String editIngredients() {
        if (ingredientsFacade.find(ingredient.getIngredientID()) != null) {
            ingredientsFacade.edit(ingredient);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditIngredient:ingredientID", new FacesMessage("ID don't exist"));
            return findIngredientforUpdate(ingredient);
        }
        return "ingredients_view";
    }

    public String resetForm() {
        ingredient = null;
        return "ingredients_view";
    }

    public String deleteIngredient(Ingredients dis) {
        ingredientsFacade.remove(dis);
        return "ingredients_view";
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public int getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(int OrderNo) {
        this.OrderNo = OrderNo;
    }
}
