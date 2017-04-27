package com.mb;

import com.facade.UserFacade;
import com.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gruby on 13.04.2017.
 */
@RequestScoped
@ManagedBean
public class LoginMB extends AbstractMB{

    @ManagedProperty(value= "#{userMB}")
    private UserMB userMB;
    private String email;
    private String password;

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
        UserFacade userFacade=new UserFacade();
        User user= userFacade.isValidLogin(email,password);

        if(user==null){
            displayErrorMessageToUser("Wrong email or login)");
            return null;
        }else{
            userMB.setUser(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",user);
            displayInfoMessageToUserAfterRedirect("Login with success!");
            return "/pages/protected/account?faces-redirect=true";
        }

    }

    public String logout(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        displayInfoMessageToUser("You have successfully logout");
        return "/pages/public/welcome?faces-redirect=true";


    }




}
