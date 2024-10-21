/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Categories;
import com.entitybeans.Dishes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Huynh
 */
@Stateless
public class DishesFacade extends AbstractFacade<Dishes> implements DishesFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DishesFacade() {
        super(Dishes.class);
    }

    @Override
    public List<Dishes> findDishesByCategory(Categories categoryId) {
        Query query = em.createQuery("SELECT d FROM Dishes d WHERE d.categoryID = :categoryID");
        query.setParameter("categoryID", categoryId);
        return query.getResultList();
    }

    @Override
    public List<Dishes> findByName(String name) {
        try {
            Query query = em.createQuery("SELECT d FROM Dishes d WHERE d.itemName LIKE :itemName");
            query.setParameter("itemName", "%" + name.trim() + "%"); // Using LIKE operator with trimmed name
            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case when no results are found
            return new ArrayList<>(); // Return an empty list instead of throwing an exception
        }
    }

    @Override
    public Dishes findDishByDishID(int dishID) {
        Query query = em.createQuery("SELECT d FROM Dishes d WHERE d.dishID = :dishID");
        query.setParameter("dishID", dishID);

        try {
            return (Dishes) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // In case no result is found
        }
    }
}
