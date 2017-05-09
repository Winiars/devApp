package com.dao;

import com.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gruby on 10.04.2017.
 */
public class UserDAO extends GenericDAO<User> {


    public UserDAO() {
        super(User.class);
    }

    public User findUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);

        return super.findOneResult(User.FIND_BY_EMAIL, parameters);
    }

    public User findUserByNick(String nick) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nick", nick);

        return super.findOneResult(User.FIND_BY_NICK, parameters);
    }


}
