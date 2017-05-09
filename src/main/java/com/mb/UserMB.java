package com.mb;

import com.facade.UserFacade;
import com.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by Gruby on 06.04.2017.
 */
@ManagedBean
@SessionScoped
public class UserMB extends AbstractMB {

    private User user;

    public boolean isRoleAdmin() {
        if (user != null) {
            return user.isAdmin();
        }
        return false;
    }

    public boolean isRoleUser() {
        if (user != null) {
            return user.isUser();
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRoleAdminOrUser() {
        return isRoleAdmin() || isRoleUser();
    }


}
