package com.facade;

import com.dao.FlatDAO;
import com.dao.RentDAO;
import com.dao.UserDAO;
import com.model.Flat;
import com.model.Rent;
import com.model.User;

/**
 * Created by Gruby on 03.05.2017.
 */
public class RentFacade {

    private RentDAO rentDAO = new RentDAO();
    private FlatDAO flatDAO = new FlatDAO();
    private UserDAO userDAO = new UserDAO();

//    public void createRent (Rent rent){
//        rentDAO.beginTransaction();
//        rentDAO.save(rent);
//        rentDAO.commitTransaction();
//        rentDAO.closeTransaction();
//
//    }


    public void addUserAndFlatToRent(int flatId, int userId, Rent rent) {
        rentDAO.beginTransaction();
        flatDAO.joinTransaction();
        userDAO.joinTransaction();

        Flat flat = flatDAO.find(flatId);
        User user = userDAO.find(userId);

        System.out.println("flat id=" + flatId);
        System.out.println("rent id=" + rent.getId());
        System.out.println("user id=" + userId);

        flat.addRent(rent);
        user.addRent(rent);

        rentDAO.save(rent);
        rentDAO.commitTransaction();
        rentDAO.closeTransaction();

    }

}
