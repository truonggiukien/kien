package com.mbeans;

import com.entitybeans.Categories;
import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import com.sessionbeans.CategoriesFacadeLocal;
import com.sessionbeans.DishIngChildFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.OrderDishChildFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

@Named(value = "dishesMB")
@RequestScoped
public class DishesMB {

    @EJB
    private OrderDishChildFacadeLocal orderDishChildFacade;

    @EJB
    private DishIngChildFacadeLocal dishIngChildFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    @EJB
    private DishesFacadeLocal dishesFacade;
    private Part file;
    private String filename;
    private Dishes dish;
    private int categoryID;

    public DishesMB() {
        dish = new Dishes();
    }

    public String addDish() {
        try {
            if (file == null) {
                dish.setPicture(null);
            } else {
                filename = file.getSubmittedFileName();
                InputStream input;

                input = file.getInputStream();
                String uniqueFilename = "dish_" + System.currentTimeMillis() + ".jpg";
                try (FileOutputStream output = new FileOutputStream("D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\products\\" + uniqueFilename)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = input.read(buf)) > 0) {
                        output.write(buf, 0, len);
                    }
                }
                dish.setPicture(uniqueFilename);
            }
            dish.setCategoryID(categoriesFacade.find(categoryID));
            dishesFacade.create(dish);
        } catch (IOException ex) {
            Logger.getLogger(DishesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        dish = null;
        return "dishes_view";
    }

    public String editDish() {
        if (dishesFacade.find(dish.getDishID()) != null) {
            try {
                if (file == null) {
                    dish.setPicture(dish.getPicture());
                } else if (file != null && file.getSubmittedFileName() != null) {
                        filename = file.getSubmittedFileName();
                        InputStream input = file.getInputStream();
                        String extension = FilenameUtils.getExtension(filename);  // Get extension from original filename
                        String uniqueFilename = "product_" + System.currentTimeMillis() + "." + extension;

                        // Delete the existing image file (if it exists)
                        String oldImagePath = "D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\products\\" + dish.getPicture();
                        File oldImageFile = new File(oldImagePath);
                        if (oldImageFile.exists()) {
                            oldImageFile.delete();
                        }

                        try (FileOutputStream output = new FileOutputStream("D:\\FeastFiesta\\FeastFiesta-war\\web\\resources\\images\\products\\" + uniqueFilename)) {
                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = input.read(buf)) > 0) {
                                output.write(buf, 0, len);
                            }
                        }
                        dish.setPicture(uniqueFilename);
                    }
                    dish.setCategoryID(categoriesFacade.find(categoryID));
                    dishesFacade.edit(dish);
                }catch (IOException ex) {
                Logger.getLogger(DishesMB.class.getName()).log(Level.SEVERE, null, ex);
            }
                return "dishes_view";
            }else {
            FacesContext.getCurrentInstance().addMessage("formEditDish:dishID", new FacesMessage("Dish ID don't exist"));
            return findDishforUpdate(dish);
        }
        }

    

    public String findDishforUpdate(Dishes dis) {
        dish = dis;
        return "dishes_edit";
    }

    public List<DishIngChild> showDishIng() {
        return dishIngChildFacade.findIngByDishID(dish);
    }

    public String deleteDish(Dishes dis) {
        dishesFacade.remove(dis);
        return "dishes_view";
    }

    public String showDishesDetail(Dishes dis) {
        dish = dis;
        return "dishes_detail";
    }

    public List<Categories> showAllCategories() {
        return categoriesFacade.findAll();
    }

    public List<Dishes> showAllDishes() {
        return dishesFacade.findAll();
    }

    public String resetForm() {
        dish = null;
        return "dishes_view";
    }
//Getter/Setter

    public Dishes getDish() {
        return dish;
    }

    public void setDish(Dishes dish) {
        this.dish = dish;
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
}
