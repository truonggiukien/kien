/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Dishes;
import com.entitybeans.Menu;
import com.entitybeans.MenuDishChild;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface MenuDishChildFacadeLocal {

    void create(MenuDishChild menuDishChild);

    void edit(MenuDishChild menuDishChild);

    void remove(MenuDishChild menuDishChild);

    MenuDishChild find(Object id);

    List<MenuDishChild> findAll();

    List<MenuDishChild> findRange(int[] range);

    int count();

    public List<MenuDishChild> findDishesByMenuID(Menu menuID);


    public void deleteByDishIDAndMenuID(Dishes dishID, Menu menuID);
    
}
