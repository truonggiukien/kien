/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Events;
import com.entitybeans.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Huynh
 */
@Stateless
public class EventsFacade extends AbstractFacade<Events> implements EventsFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventsFacade() {
        super(Events.class);
    }

    @Override
    public List<Events> findByName(String name) {
        try {
            Query query = em.createQuery("SELECT e FROM Events e WHERE e.eventName = :eventName");
            query.setParameter("eventName", name); // Using LIKE operator with trimmed name
            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case when no results are found
            return new ArrayList<>(); // Return an empty list instead of throwing an exception
        }
    }

    @Override
    public Events findEventByVoucherCode(String voucherCode) {
        TypedQuery<Events> query = em.createQuery("SELECT e FROM Events e WHERE e.eventName = :voucherCode", Events.class);
        query.setParameter("voucherCode", voucherCode);
        List<Events> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

}
