package com.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * Created by Gruby on 11.04.2017.
 */
public class Messages {
    public void sendInfoMessageToUser(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }

    public void sendErrorMessageToUser(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
        addMessageToJsfContext(facesMessage);
    }

    private FacesMessage createMessage(FacesMessage.Severity severity, String message) {
        return new FacesMessage(severity, message, message);
    }

    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);

    }

    public void sendInfoMessageToUserAfterReddirect(String message){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
//        flash.setRedirect(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));

    }
}