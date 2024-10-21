/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Menu;
import com.entitybeans.MenuDishChild;
import com.sessionbeans.MenuDishChildFacadeLocal;
import com.sessionbeans.MenuFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Huynh
 */
@Named(value = "menuListMB")
@RequestScoped
public class MenuListMB {

    @EJB
    private MenuDishChildFacadeLocal menuDishChildFacade;

    @EJB
    private MenuFacadeLocal menuFacade;
    private Menu menu;
    private String searchTerm;

    public MenuListMB() {
        menu = new Menu();
    }

    public List<Menu> showAllMenu() {
        if (searchTerm != null) {
            return menuFacade.findByName(searchTerm);
        } else {
            return menuFacade.findAll();
        }
    }

    public List<MenuDishChild> showDishMenu(Menu menuID) {
        return menuDishChildFacade.findDishesByMenuID(menuID);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}
