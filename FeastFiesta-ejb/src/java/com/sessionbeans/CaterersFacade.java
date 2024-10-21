/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Caterers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Huynh
 */
@Stateless
public class CaterersFacade extends AbstractFacade<Caterers> implements CaterersFacadeLocal {

    @PersistenceContext(unitName = "FeastFiesta-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaterersFacade() {
        super(Caterers.class);
    }
    
}
