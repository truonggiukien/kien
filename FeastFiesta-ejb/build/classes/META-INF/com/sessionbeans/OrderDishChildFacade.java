/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Dishes;
import com.entitybeans.OrderDishChild;
import com.entitybeans.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Huynh
 */
@Stateless
public class OrderDishChildFacade extends AbstractFacade<OrderDishChild> implements OrderDishChildFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDishChildFacade() {
        super(OrderDishChild.class);
    }

    
    @Override
    public List<OrderDishChild> findOrderDishByOrderID(Orders orderNo) {
        Query query = em.createQuery("SELECT o FROM OrderDishChild o WHERE o.orderNo = :orderNo");
        query.setParameter("orderNo", orderNo);
        return query.getResultList();
    }

    @Override
    public List<Dishes> recommendDishesByDishID(Dishes dishID) {
        // Find orders containing the specified dishID
        Query query = em.createQuery("SELECT o.orderNo FROM OrderDishChild o WHERE o.dishID = :dishID");
        query.setParameter("dishID", dishID);
        List<Orders> orderNumbers = query.getResultList();

        List<Dishes> recommendedDishes = new ArrayList<>();

        // For each order found, find other dishes in the same order
        for (Orders orderNo : orderNumbers) {
            Query dishQuery = em.createQuery("SELECT d.dishID FROM OrderDishChild d WHERE d.orderNo = :orderNo AND d.dishID != :dishID");
            dishQuery.setParameter("orderNo", orderNo);
            dishQuery.setParameter("dishID", dishID);
            List<Dishes> relatedDishes = dishQuery.getResultList();

            for (Dishes relatedDish : relatedDishes) {
                if (!recommendedDishes.contains(relatedDish)) {
                    recommendedDishes.add(relatedDish);
                }
            }
        }

        return recommendedDishes;
    }

}
