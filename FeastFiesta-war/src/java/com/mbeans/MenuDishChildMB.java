package com.mbeans;

import com.entitybeans.Dishes;
import com.entitybeans.MenuDishChild;
import com.entitybeans.Menu;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.MenuDishChildFacadeLocal;
import com.sessionbeans.MenuFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "menuDishChildMB")
@RequestScoped
public class MenuDishChildMB {

    @EJB
    private DishesFacadeLocal dishesFacade;
    @EJB
    private MenuDishChildFacadeLocal menuDishChildFacade;
    @EJB
    private MenuFacadeLocal menuFacade;
    
    private MenuDishChild menuDishChild;
    private Menu menu;
    private int menuID;
    private int dishID;
    
    public MenuDishChildMB() {
        menuDishChild = new MenuDishChild();
        menu = new Menu();
    }
    
    public String createMenuDishChild() {
        menuDishChild.setDishID(dishesFacade.find(this.dishID));
        menuDishChild.setMenuID(menuFacade.find(this.menuID));
        menuDishChildFacade.create(menuDishChild);
        return "menu_view";
    }
    
    public String editMenuDishChild() {
        MenuDishChild existingMenuDishChild = menuDishChildFacade.find(menuDishChild.getMenuDishID());
        if (existingMenuDishChild != null) {
              menuDishChild.setDishID(dishesFacade.find(this.dishID));
        menuDishChild.setMenuID(menuFacade.find(this.menuID));
            menuDishChildFacade.edit(existingMenuDishChild);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditMenuDishChild", new FacesMessage("Menu Dish Child ID don't exist"));
            return findDishesforUpdate(menuDishChild);
        }
        return "menu_view";
    }
    
    
    public List<MenuDishChild> showAllMenuDishChildren() {
        return menuDishChildFacade.findAll();
    }
    
    public List<Menu> showAllMenus() {
        return menuFacade.findAll();
    }
    
    public List<Dishes> showAllDishes() {
        return dishesFacade.findAll();
    }
     public String findMenutoAdd(Menu mn) {
        menu = mn;
        return "menudish_add";
    }
    public String findDishesforUpdate(MenuDishChild dish) {
        menuDishChild = dish;
        return "menu_edit";
    }
    
    public String deleteMenuDishChild(MenuDishChild dish) {
        menuDishChildFacade.remove(dish);
        return "menu_view";
    }
    
    public String resetForm() {
        menuDishChild = new MenuDishChild();
        return "menu_view";
    }
    
    public MenuDishChild getMenuDishChild() {
        return menuDishChild;
    }
    
    public void setMenuDishChild(MenuDishChild menuDishChild) {
        this.menuDishChild = menuDishChild;
    }
    
    public int getMenuID() {
        return menuID;
    }
    
    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }
    
    public int getDishID() {
        return dishID;
    }
    
    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
}
