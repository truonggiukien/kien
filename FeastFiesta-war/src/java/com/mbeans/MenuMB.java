package com.mbeans;

import com.entitybeans.Categories;
import com.entitybeans.Dishes;
import com.entitybeans.Menu;
import com.entitybeans.MenuDishChild;
import com.sessionbeans.CategoriesFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.MenuDishChildFacadeLocal;
import com.sessionbeans.MenuFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

@Named(value = "menuMB")
@RequestScoped
public class MenuMB {

    @EJB
    private DishesFacadeLocal dishesFacade;

    @EJB
    private MenuDishChildFacadeLocal menuDishChildFacade;

    @EJB
    private MenuFacadeLocal menuFacade;
    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    @EJB
    private DishesFacadeLocal menuesFacade;

    private Part file;
    private String filename;
    private Menu menu;
    private MenuDishChild menudish;
    private int categoryID;
    private int menuID;

    public MenuMB() {
        menu = new Menu();
    }

    public String addMenu() {
        try {
            if (file == null) {
                menu.setPicture(null);
            } else {
                filename = file.getSubmittedFileName();
                InputStream input;

                input = file.getInputStream();
                String uniqueFilename = "menu_" + System.currentTimeMillis() + ".jpg";
                try (FileOutputStream output = new FileOutputStream("D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\menu\\" + uniqueFilename)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = input.read(buf)) > 0) {
                        output.write(buf, 0, len);
                    }
                }
                menu.setPicture(uniqueFilename);
            }
            menuFacade.create(menu);
        } catch (IOException ex) {
            Logger.getLogger(DishesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "menu_view";
    }

    public String editMenu() {
        if (menuFacade.find(menu.getMenuID()) != null) {
            try {
                // Check if a new image is uploaded
                if (file == null) {
                    menu.setPicture(menu.getPicture());
                } else if (file != null && file.getSubmittedFileName() != null) {
                    filename = file.getSubmittedFileName();
                    InputStream input = file.getInputStream();
                    String extension = FilenameUtils.getExtension(filename);  // Get extension from original filename
                    String uniqueFilename = "menu_" + System.currentTimeMillis() + "." + extension;

                    // Delete the existing image file (if it exists)
                    String oldImagePath = "D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\menu\\" + menu.getPicture();
                    File oldImageFile = new File(oldImagePath);
                    if (oldImageFile.exists()) {
                        oldImageFile.delete();
                    }

                    try (FileOutputStream output = new FileOutputStream("D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\menu\\" + uniqueFilename)) {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = input.read(buf)) > 0) {
                            output.write(buf, 0, len);
                        }
                    }
                    menu.setPicture(uniqueFilename);
                }
                menuFacade.edit(menu);
            } catch (IOException ex) {
                Logger.getLogger(DishesMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "menu_view";
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditMenu:menuID", new FacesMessage("ID don't exist"));
            return findMenuForUpdate(menu);
        }
    }

    public List<MenuDishChild> showMenuDish() {
        return menuDishChildFacade.findDishesByMenuID(menu);
    }

    public String findMenuForUpdate(Menu m) {
        menu = m;
        return "menu_edit";
    }

    public String findMenuDetail(Menu m) {
        menu = m;
        return "menu_detail";
    }

    public String deleteDish(MenuDishChild md) {
        menuDishChildFacade.remove(md);
        return "ingredients_view";
    }

    public String deleteMenu(Menu m) {
        menuFacade.remove(m);
        return "menu_view";
    }

    public List<Menu> showAllMenu() {
        return menuFacade.findAll();
    }

    public String resetForm() {
        menu = null;
        return "menu_view";
    }

    public List<Categories> getAllCategories() {
        return categoriesFacade.findAll();
    }

    public List<Dishes> getAllDishes() {
        return menuesFacade.findAll();
    }

    // Getter/Setter
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MenuDishChild getMenudish() {
        return menudish;
    }

    public void setMenudish(MenuDishChild menudish) {
        this.menudish = menudish;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

}
