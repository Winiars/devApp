package com.mb;

import com.facade.UserFacade;
import com.model.Role;
import com.model.User;
import org.primefaces.event.FlowEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Gruby on 11.04.2017.
 */

@ManagedBean
@SessionScoped
public class RegisterMB extends AbstractMB implements Serializable {


    private UserFacade userFacade;
    private User user;


    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserFacade getUserFacade() {
        if (userFacade == null) {
            this.userFacade = new UserFacade();
        }
        return userFacade;
    }

    public void createUser() {

        this.user.setRole(Role.USER);
        getUserFacade().createUser(user);

        displayInfoMessageToUserAfterRedirect("Zarejestrowano użytkownika " + user.getNick());

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return "welcome?faces-redirect=true";
    }

    public void propertyTest() {
        displayInfoMessageToUser("cos tam" + getPropertyMessageValueFromCustomMessageBundle("passwordenter"));
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

}
