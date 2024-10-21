package com.mbeans;

import com.entitybeans.Categories;
import com.sessionbeans.CategoriesFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author STUDENT
 */
@Named(value = "categoriesMB")
@RequestScoped
public class CategoriesMB {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private Categories category;

    public CategoriesMB() {
        category = new Categories();
    }

    public String addCategories() {
        if (category.getCategoryID() == null) {
            categoriesFacade.create(category);
        } else {
            categoriesFacade.edit(category);
        }
        return "categories_view";
    }

    public String editCategories() {
        if (categoriesFacade.find(category.getCategoryID()) != null) {
            categoriesFacade.edit(category);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditCategory:categoryID", new FacesMessage("ID don't exist"));
            return findCategoryforUpdate(category);
        }
        return "categories_view";
    }
    public String deleteCategories(Categories cat) {
        categoriesFacade.remove(cat);
        return "categories_view";
    }

    public String findCategoryforUpdate(Categories cat) {
        category = cat;
        return "categories_edit";
    }

    public List<Categories> showAllCategories() {
        return categoriesFacade.findAll();
    }

    public String resetForm() {
        category = new Categories();
        return "categories_view";
    }
    

    // Getters and Setters
    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
