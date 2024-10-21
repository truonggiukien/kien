/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Customers;
import com.entitybeans.Dishes;
import com.entitybeans.FavoriteList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface FavoriteListFacadeLocal {

    void create(FavoriteList favoriteList);

    void edit(FavoriteList favoriteList);

    void remove(FavoriteList favoriteList);

    FavoriteList find(Object id);

    List<FavoriteList> findAll();

    List<FavoriteList> findRange(int[] range);

    int count();

    public boolean isDishInFavorites(Customers customer, Dishes dish);
    
    public List<FavoriteList> findFavoritesByCustomerID(Customers customerID);
    
}
