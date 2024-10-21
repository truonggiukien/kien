/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Caterers;
import com.sessionbeans.CaterersFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Huynh
 */
@Named(value = "caterersMB")
@RequestScoped
public class CaterersMB {

    @EJB
    private CaterersFacadeLocal caterersFacade;

    private Caterers caterer;
    private String dateJoin;

    public Caterers getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterers caterer) {
        this.caterer = caterer;
    }

    public CaterersMB() {
        caterer = new Caterers();
    }

    public String addCaterers() throws ParseException {
        if (caterer.getCatererId() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(dateJoin);
            caterer.setDateOfJoin(parsedDate);
            caterersFacade.create(caterer);
        } else {
            caterersFacade.edit(caterer);
        }
        return "caterers_view";
    }

    public String editCaterers() {
        if (caterersFacade.find(caterer.getCatererId()) != null) {
            caterersFacade.edit(caterer);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditCaterer:catererID", new FacesMessage("ID don't exist"));
            return findCatererforUpdate(caterer);
        }
        return "caterers_view";
    }

    public String deleteCaterers(Caterers cat) {
        caterersFacade.remove(cat);
        return "caterers_view";
    }

    public String findCatererforUpdate(Caterers cat) {
        caterer = cat;
        return "caterers_edit";
    }

    public String resetForm() {
        caterer = null;
        return "caterers_view";
    }

    public List<Caterers> showAllCaterers() {
        return caterersFacade.findAll();
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

}
