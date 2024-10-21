/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Events;
import com.sessionbeans.EventsFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huynh
 */
@Named(value = "eventsMB")
@RequestScoped
public class EventsMB {

    @EJB
    private EventsFacadeLocal eventsFacade;

    private Events event;

    public EventsMB() {
        event = new Events();
    }

    public String addEvents() {
        if (event.getEventID() == null) {
            eventsFacade.create(event);
        } else {
            eventsFacade.edit(event);
        }
        return "events_view";
    }

    public String editEvents() {
        if (eventsFacade.find(event.getEventID()) != null) {
            eventsFacade.edit(event);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditEvent:eventID", new FacesMessage("ID don't exist"));
            return findEventforUpdate(event);
        }
        return "events_view";
    }
    public String deleteEvents(Events cat) {
        eventsFacade.remove(cat);
        return "events_view";
    }

    public String findEventforUpdate(Events cat) {
        event = cat;
        return "events_edit";
    }

    public List<Events> showAllEvents() {
        return eventsFacade.findAll();
    }

    public String resetForm() {
        event = new Events();
        return "events_view";
    }
    
    public String formatDay(Date date) {
    // Định dạng đầu vào
    SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    // Định dạng đầu ra
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    // Chuyển đổi ngày
    return outputFormat.format(date);
}

    // Getters and Setters
    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
    
}
