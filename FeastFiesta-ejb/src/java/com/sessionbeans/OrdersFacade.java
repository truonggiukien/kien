/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
import com.entitybeans.Orders;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Huynh
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findOrdersByMonthAndYear(int month, int year) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM Orders o WHERE MONTH(o.orderDate) = ?1 AND YEAR(o.orderDate) = ?2", Orders.class);
            query.setParameter(1, month);
            query.setParameter(2, year);
            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case when no results are found
            return new ArrayList<>(); // Return an empty list instead of throwing an exception
        }
    }

    @Override
    public List<Orders> findOrdersByStatus(String stt) {
        Query query = getEntityManager().createQuery("SELECT o FROM Orders o WHERE o.status = :status");
        query.setParameter("status", stt); // Using LIKE operator with trimmed name
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public List<Orders> findOrdersByCustomerID(Customers cus) {
        Query query = getEntityManager().createQuery("SELECT o FROM Orders o WHERE o.customerID = :customerID");
        query.setParameter("customerID", cus); // Using LIKE operator with trimmed name
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Orders> findOrdersByMonthYearAndStatus(int month, int year, String status) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM Orders o WHERE MONTH(o.orderDate) = ?1 AND YEAR(o.orderDate) = ?2 AND o.status = ?3", Orders.class);
            query.setParameter(1, month);
            query.setParameter(2, year);
            query.setParameter(3, status);
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
