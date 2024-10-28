package com.mbeans;

import com.entitybeans.Events;
import com.sessionbeans.EventsFacadeLocal;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@Named(value = "eventsMB")
@RequestScoped
public class EventsMB {

    @EJB
    private EventsFacadeLocal eventsFacade;

    private Events event;

    private Part eventImage;
    private String imagePath;

    public EventsMB() {
        event = new Events();
    }

    public Part getEventImage() {
        return eventImage;
    }

    public void setEventImage(Part eventImage) {
        this.eventImage = eventImage;
    }

    public String addEvents() {
        if (event.getEventID() == null) {
            saveImage();
            eventsFacade.create(event);
            return "events_view";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event already exists."));
            return null;
        }
    }

    public String editEvents() {
        if (eventsFacade.find(event.getEventID()) != null) {
            saveImage();
            eventsFacade.edit(event);
            return "events_view";
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditEvent:eventID", new FacesMessage("ID doesn't exist"));
            return "events_edit";
        }
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
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return outputFormat.format(date);
    }

    private void saveImage() {
        if (eventImage != null) {
            try (InputStream input = eventImage.getInputStream()) {
                String fileName = "uploads/" + eventImage.getSubmittedFileName();
                File file = new File(fileName);
                try (FileOutputStream output = new FileOutputStream(file)) {
                    input.transferTo(output);
                    event.setEventName(fileName); // Update event with the image path
                }
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Image upload failed."));
            }
        }
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
