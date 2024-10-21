/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
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
public class CustomersFacade extends AbstractFacade<Customers> implements CustomersFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }

    @Override
    public Customers findCustomerbyUsername(String username) {
        try {
            Query query = em.createNamedQuery("Customers.findByUsername", Customers.class);
            query.setParameter("username", username);
            return (Customers) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        return !em.createQuery("SELECT c FROM Customers c WHERE c.username = :username", Customers.class)
                .setParameter("username", username)
                .getResultList().isEmpty();
    }

    @Override
    public boolean isEmailExists(String email) {
        return !em.createQuery("SELECT c FROM Customers c WHERE c.email = :email", Customers.class)
                .setParameter("email", email)
                .getResultList().isEmpty();
    }

    @Override
    public Customers findCustomerByEmail(String email) {
        try {
            return em.createQuery("SELECT c FROM Customers c WHERE c.email = :email", Customers.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;  // Trả về null nếu không tìm thấy email
        }
    }
}
