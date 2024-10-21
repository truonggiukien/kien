/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Categories;
import com.entitybeans.DishIngChild;
import com.entitybeans.Dishes;
import com.sessionbeans.CategoriesFacadeLocal;
import com.sessionbeans.DishIngChildFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import com.sessionbeans.IngredientsFacadeLocal;
import com.sessionbeans.OrderDishChildFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huynh
 */
@Named(value = "dishListMB")
@RequestScoped
public class DishListMB {

    @EJB
    private IngredientsFacadeLocal ingredientsFacade;

    @EJB
    private DishIngChildFacadeLocal dishIngChildFacade;

    @EJB
    private OrderDishChildFacadeLocal orderDishChildFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private DishesFacadeLocal dishesFacade;
    private String searchTerm;
    private Dishes dish;
    private Categories categories;

    public DishListMB() {
        dish = new Dishes();
    }

    public List<Dishes> showAllDishes() {
        return dishesFacade.findAll(); // Show all dishes if cat is null or empty
    }

    public List<Dishes> showDishKind(String cat) {
        if (cat == null || cat.isEmpty()) {
            return dishesFacade.findDishesByCategory(null); // Return all dishes if cat is null or empty
        } else {
            Categories category = categoriesFacade.findByName(cat);
            List<Dishes> dishesList = dishesFacade.findDishesByCategory(category);

            if (dishesList.size() > 5) {
                return dishesList.subList(0, 5); // Return only the first 5 dishes
            } else {
                return dishesList; // Return all dishes if there are 5 or fewer
            }
        }
    }

    public List<DishIngChild> showDishIng() {
        return dishIngChildFacade.findIngByDishID(dish);
    }
    public List<Dishes> showRecommend(int dishID) {

        return orderDishChildFacade.recommendDishesByDishID(dishesFacade.find(dishID));
    }

    public String resetForm() {
        dish = null;
        return "listproducts";
    }
    public List<DishIngChild> showDishIngredients() {
        return ingredientsFacade.findDishIngChildByDishID(dishesFacade.find(dish.getDishID()));
    }

    public String showDishesByCategory(String categoryName) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedCategory", categoryName);
        return "listproducts";
    }

    public List<Categories> showAllCategories() {
        return categoriesFacade.findAll();
    }

    public String clearCategory() {
        // Clear the selected category from the session
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedCategory");
        return "listproducts"; // Replace with your navigation outcome
    }

    public String showSearch() {
        return null;
    }

    public List<Dishes> showDishes() {
        String selectedCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedCategory");

        if (searchTerm != null) {
            return dishesFacade.findByName(searchTerm);
        } else if (selectedCategory != null && !selectedCategory.isEmpty()) {
            // Filter dishes based on selectedCategory
            return dishesFacade.findDishesByCategory(categoriesFacade.findByName(selectedCategory));
        } else {
            // Show all dishes if no searchTerm or selectedCategory is provided
            return dishesFacade.findAll();
        }
    }

    public String searchProducts() {
        FacesContext.getCurrentInstance().renderResponse();
        return null;
    }

    public List<Dishes> showAllMainCourses() {
        return dishesFacade.findDishesByCategory(categoriesFacade.findByName("Main Courses"));
    }

    public List<Dishes> showAllAppetizers() {
        return dishesFacade.findDishesByCategory(categoriesFacade.findByName("Appetizers"));
    }

    public List<Dishes> showAllSideDishes() {
        return dishesFacade.findDishesByCategory(categoriesFacade.findByName("Side Dishes"));
    }

    public List<Dishes> showAllDesserts() {
        return dishesFacade.findDishesByCategory(categoriesFacade.findByName("Desserts"));
    }

    public List<Dishes> showAllBeverages() {
        return dishesFacade.findDishesByCategory(categoriesFacade.findByName("Beverages"));
    }

    public String findDishforShow(int id) {
        dish = dishesFacade.find(id);
        return "/user/detailproduct";
    }

    public Dishes getDish() {
        return dish;
    }

    public void setDish(Dishes dish) {
        this.dish = dish;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
