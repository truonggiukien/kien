/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import com.entitybeans.Ingredients;
import com.entitybeans.OrderDishChild;
import com.entitybeans.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Huynh
 */
@Stateless
public class IngredientsFacade extends AbstractFacade<Ingredients> implements IngredientsFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientsFacade() {
        super(Ingredients.class);
    }

    @Override
    public List<Ingredients> findIngredientsByOrder(Orders order) {
        List<Ingredients> ingredientsList = new ArrayList<>();

        // Find all dishIDs associated with the given order in OrderDishChild table
        Query orderDishQuery = em.createQuery("SELECT o.dishID FROM OrderDishChild o WHERE o.orderNo = :order");
        orderDishQuery.setParameter("order", order);
        List<Dishes> dishIDs = orderDishQuery.getResultList();

        // Find all ingredients associated with each dishID
        for (Dishes dishID : dishIDs) {
            Query dishIngQuery = em.createQuery("SELECT d.ingredientID FROM DishIngChild d WHERE d.dishID = :dishID");
            dishIngQuery.setParameter("dishID", dishID);
            List<Ingredients> ingredientIDs = dishIngQuery.getResultList();

            for (Ingredients ingredient : ingredientIDs) {
                if (!ingredientsList.contains(ingredient)) {
                    ingredientsList.add(ingredient);
                }
            }
        }

        return ingredientsList;
    }

    @Override
    public List<DishIngChild> findDishIngredientsByOrder(Orders order) {
        Query orderDishQuery = em.createQuery("SELECT o.dishID FROM OrderDishChild o WHERE o.orderNo = :order");
        orderDishQuery.setParameter("order", order);
        List<Dishes> dishIDs = orderDishQuery.getResultList();

        List<DishIngChild> dishIngChildren = new ArrayList<>();
        for (Dishes dishID : dishIDs) {
            Query dishIngQuery = em.createQuery("SELECT d FROM DishIngChild d WHERE d.dishID = :dishID");
            dishIngQuery.setParameter("dishID", dishID);
            dishIngChildren.addAll(dishIngQuery.getResultList());
        }

        return dishIngChildren;
    }
    @Override
    public List<DishIngChild> findDishIngChildByDishID(Dishes dishID) {
        return em.createQuery("SELECT d FROM DishIngChild d WHERE d.DishID = :dishID", DishIngChild.class)
                .setParameter("dishID", dishID)
                .getResultList();
    }

}
