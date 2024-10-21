package com.mbeans;

import com.entitybeans.Customers;
import com.entitybeans.FavoriteList;
import com.entitybeans.Dishes;
import com.sessionbeans.CustomersFacadeLocal;
import com.sessionbeans.FavoriteListFacadeLocal;
import com.sessionbeans.DishesFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh
 */
@Named(value = "favoriteListMB")
@RequestScoped
public class FavoriteListMB {

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private FavoriteListFacadeLocal favoriteListFacade;

    @EJB
    private DishesFacadeLocal dishesFacade;

    private FavoriteList favoriteList;

    // Retrieve only the favorite list for the current customer
    public List<FavoriteList> find() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Integer customerID = (Integer) session.getAttribute("customerID");
        if (customerID != null) {
            return favoriteListFacade.findFavoritesByCustomerID(customersFacade.find(customerID));
        }
        return null;
    }

    public String addToFavorite(int dishID) {
        // Lấy món ăn từ cơ sở dữ liệu
        Dishes dish = dishesFacade.find(dishID);

        // Lấy thông tin người dùng hiện tại
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Integer customerID = (Integer) session.getAttribute("customerID");

        if (customerID != null && dish != null) {
            // Kiểm tra xem món ăn đã tồn tại trong danh sách yêu thích hay chưa
            Customers customer = customersFacade.find(customerID);
            boolean isDishInFavorites = favoriteListFacade.isDishInFavorites(customer, dish);

            if (isDishInFavorites) {
                // Thông báo lỗi nếu món ăn đã có trong danh sách yêu thích
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Món ăn này đã có trong danh sách yêu thích!", null));
            } else {
                // Nếu chưa có, tạo một thực thể FavoriteList mới và thêm vào cơ sở dữ liệu
                favoriteList = new FavoriteList();
                favoriteList.setCustomerID(customer);
                favoriteList.setDishID(dish);

                // Thêm vào cơ sở dữ liệu
                favoriteListFacade.create(favoriteList);

                // Thông báo thành công
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Món ăn đã được thêm vào danh sách yêu thích!"));
            }
        } else {
            // Thông báo lỗi nếu không thể thêm món ăn vào danh sách yêu thích
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Không thể thêm món ăn vào danh sách yêu thích", null));
        }

        return "home";  // Điều hướng tới trang chủ hoặc trang yêu thích
    }

    public void removeFavorite(FavoriteList favorite) {
        if (favorite != null) {
            favoriteListFacade.remove(favorite);
            // Optionally, refresh the list or handle navigation
        }
    }

    public void removeFromFavorite(Integer favoriteListID) {
        FavoriteList favoriteToRemove = favoriteListFacade.find(favoriteListID);
        if (favoriteToRemove != null) {
            favoriteListFacade.remove(favoriteToRemove);
        }
    }

    private Customers getCurrentCustomer() {
        // Implement this method to return the current logged-in customer
        // For example, retrieve it from the session
        return null;
    }

    public FavoriteListMB() {
        favoriteList = new FavoriteList();
    }

    public FavoriteList getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(FavoriteList favoriteList) {
        this.favoriteList = favoriteList;
    }
}
