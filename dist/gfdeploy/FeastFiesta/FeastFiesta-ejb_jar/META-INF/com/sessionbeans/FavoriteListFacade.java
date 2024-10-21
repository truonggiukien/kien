/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
import com.entitybeans.Dishes;
import com.entitybeans.FavoriteList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Huynh
 */
@Stateless
public class FavoriteListFacade extends AbstractFacade<FavoriteList> implements FavoriteListFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoriteListFacade() {
        super(FavoriteList.class);
    }

    @Override
    public boolean isDishInFavorites(Customers customer, Dishes dish) {
        // Sử dụng JPQL để kiểm tra món ăn đã có trong danh sách yêu thích của khách hàng chưa
        String query = "SELECT COUNT(f) FROM FavoriteList f WHERE f.customerID = :customer AND f.dishID = :dish";
        Long count = (Long) em.createQuery(query)
                .setParameter("customer", customer)
                .setParameter("dish", dish)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public List<FavoriteList> findFavoritesByCustomerID(Customers customerID) {
        // Retrieve the list of favorite dishes for a specific customer
        TypedQuery<FavoriteList> query = em.createQuery("SELECT f FROM FavoriteList f WHERE f.customerID = :customerID", FavoriteList.class);
        query.setParameter("customerID", customerID);
        return query.getResultList();
    }
}
