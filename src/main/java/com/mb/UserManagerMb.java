package com.mb;

import com.facade.UserFacade;
import com.model.Role;
import com.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Gruby on 24.04.2017.
 */
@ManagedBean
@ViewScoped
public class UserManagerMb extends AbstractMB {

    private UserFacade userFacade;
    private List<User> listOfUsers;
    private User user;

    public List<User> getListOfUsers() {
        return this.listOfUsers = getUserFacade().listOfAll();
    }

    private UserFacade getUserFacade() {
        if (this.userFacade == null) {
            userFacade = new UserFacade();
        }
        return userFacade;
    }

    public void resetUser() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateUser() {
        try {
            getUserFacade().updateUser(this.user);
            resetUser();
            super.displayInfoMessageToUser("User updated with success");
        } catch (Exception e) {
            e.printStackTrace();
            super.displayErrorMessageToUser("Oops we couldn't update user");
        }
    }

    public Role[] getRoles() {
        return Role.values();
    }
}
