/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Dishes;
import com.entitybeans.Menu;
import com.entitybeans.MenuDishChild;
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
public class MenuDishChildFacade extends AbstractFacade<MenuDishChild> implements MenuDishChildFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuDishChildFacade() {
        super(MenuDishChild.class);
    }

    @Override
    public List<MenuDishChild> findDishesByMenuID(Menu menuID) {
        Query query = em.createQuery("SELECT d FROM MenuDishChild d WHERE d.menuID = :menuID");
        query.setParameter("menuID", menuID);
        return query.getResultList();
    }

    @Override
    public void deleteByDishIDAndMenuID(Dishes dishID, Menu menuID) {
        Query query = em.createQuery("DELETE FROM MenuDishChild d WHERE d.dishID = :dishID AND d.menuID = :menuID");
        query.setParameter("dishID", dishID);
        query.setParameter("menuID", menuID);
        query.executeUpdate();
    }
}
