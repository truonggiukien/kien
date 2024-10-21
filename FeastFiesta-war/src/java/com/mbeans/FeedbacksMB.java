/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Feedbacks;
import com.sessionbeans.CustomersFacadeLocal;
import com.sessionbeans.FeedbacksFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh
 */
@Named(value = "feedbacksMB")
@RequestScoped
public class FeedbacksMB {

     @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;
    private Feedbacks feedback;
    private String tempmess;

    public FeedbacksMB() {
        feedback = new Feedbacks();
    }

    public String addFeedback() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        feedback.setCustomerID(customersFacade.find(session.getAttribute("customerID")));
        feedbacksFacade.create(feedback);
        return "home";
    }

    public List<Feedbacks> showAllFeedback() {
        return feedbacksFacade.findAll();
    }

    public Feedbacks getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedbacks feedback) {
        this.feedback = feedback;
    }

    public String getTempmess() {
        return tempmess;
    }

    public void setTempmess(String tempmess) {
        this.tempmess = tempmess;
    }
    
}
