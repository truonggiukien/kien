/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Huynh
 */
@Stateless
public class DishIngChildFacade extends AbstractFacade<DishIngChild> implements DishIngChildFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DishIngChildFacade() {
        super(DishIngChild.class);
    }

    @Override
    public List<DishIngChild> findIngByDishID(Dishes dishID) {
        Query query = em.createQuery("SELECT d FROM DishIngChild d WHERE d.dishID = :dishID");
        query.setParameter("dishID", dishID);
        return query.getResultList();
    }

}
