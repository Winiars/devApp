package com.facade;

import com.dao.UserDAO;
import com.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gruby on 10.04.2017.
 */
public class UserFacade implements Serializable {

    private UserDAO userDAO= new UserDAO();

    public void createUser (User user){
        userDAO.beginTransaction();
        userDAO.save(user);
        userDAO.commitTransaction();
        userDAO.closeTransaction();

    }

    public boolean emailAlreadyExists(String email) {
        userDAO.beginTransaction();
        User user = userDAO.findUserByEmail(email);
        userDAO.closeTransaction();
        if (user == null) {

            return false;
        }

        return true;
    }

    public boolean nickAlreadyExists(String nick) {
        userDAO.beginTransaction();
        User user = userDAO.findUserByNick(nick);
        userDAO.closeTransaction();
        if (user == null) {

            return false;
        }

        return true;
    }

    public User isValidLogin(String email, String password) {
        userDAO.beginTransaction();
        User user = userDAO.findUserByEmail(email);
        userDAO.closeTransaction();
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    public List<User> listOfAll(){
        userDAO.beginTransaction();
        List<User> listOfUsers=userDAO.findAll();
        userDAO.closeTransaction();
        return listOfUsers;

    }


    public void updateUser(User user){
        userDAO.beginTransaction();
        User userToUpdate=userDAO.find(user.getId());
        userToUpdate.setRole(user.getRole());
        userDAO.update(userToUpdate);
        userDAO.commitTransaction();
        userDAO.closeTransaction();
    }



}
