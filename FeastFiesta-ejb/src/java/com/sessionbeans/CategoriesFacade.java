/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Categories;
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
public class CategoriesFacade extends AbstractFacade<Categories> implements CategoriesFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriesFacade() {
        super(Categories.class);
    }

    @Override
    public Categories findByName(String name) {
        Query query = getEntityManager().createQuery("SELECT c FROM Categories c WHERE c.name LIKE :name");
        query.setParameter("name", "%" + name.trim() + "%"); // Using LIKE operator with trimmed name

        try {
            return (Categories) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Category not found with the given name
        }
    }
}
