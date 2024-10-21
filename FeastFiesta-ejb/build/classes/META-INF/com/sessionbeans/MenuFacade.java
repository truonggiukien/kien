/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;


import com.entitybeans.Menu;
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
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    @Override
      public List<Menu> findByName(String name) {
        try {
            Query query = em.createQuery("SELECT m FROM Menu m WHERE m.menuName like :menuName");
            query.setParameter("menuName", "%" + name.trim() + "%"); // Using LIKE operator with trimmed name
            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case when no results are found
            return new ArrayList<>(); // Return an empty list instead of throwing an exception
        }
    }
    
}
