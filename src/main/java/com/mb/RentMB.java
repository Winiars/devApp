package com.mb;

import com.facade.RentFacade;
import com.model.Flat;
import com.model.Rent;
import com.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Gruby on 03.05.2017.
 */
@ManagedBean
@ViewScoped
public class RentMB extends AbstractMB {

    private RentFacade rentFacade;

    private Rent rent;

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;


    @ManagedProperty(value = "#{flatMB}")
    private FlatMB flatMB;


    private User user;

    private int flatId;

    public int getFlatId() {
        return flatId;
    }

    public void setFlatId(int flatId) {

        System.out.println("teraz dodal flat " + flatId);
        this.flatId = flatId;
    }

    public void setFlatMB(FlatMB flatMB) {
        this.flatMB = flatMB;
    }

    public FlatMB getFlatMB() {
        return this.flatMB;
    }


    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public User getUser() {
        if (user == null) {
            this.user = userMB.getUser();
        }
        return user;
    }

    public RentFacade getRentFacade() {
        if (rentFacade == null) {
            rentFacade = new RentFacade();
        }
        return rentFacade;
    }

    public Rent getRent() {
        if (rent == null) {
            rent = new Rent();
        }
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void createRent() {
        try {
            System.out.println("teraz jest w create Rent");
            int userId = getUser().getId();
            Flat flatToUpdate = (Flat) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flat");
            int flatId = flatToUpdate.getId();
            System.out.println("to jest user id= " + userId + "a to jest flat id" + flatId);
            getRentFacade().addUserAndFlatToRent(flatId, userId, getRent());
            resetRent();
            displayInfoMessageToUserAfterRedirect("Flat booked successfully!");
        } catch (Exception e) {
            displayErrorMessageToUser("Oops we couldn't book flat for you");
            e.printStackTrace();
        }

    }

//    public boolean rentingIsAvailable() {
//        getFlat();
//        getRent();
//        boolean rentStartsAfterBeginingOfRentingTime = flat.getStartDate().before(rent.getStartDate());
//        boolean rentFinishesBeforeEndOfRentingTime = flat.getFinishDate().after(rent.getFinishDate());
//
//        List<Rent> listOfRents = flat.getRents();
//
//        for (Rent rented : listOfRents) {
//            Date endOfRent = rented.getFinishDate();
//            Date startOfRent = rented.getStartDate();
//
//            if (endOfRent.after(rent.getStartDate())) {
//                return true;
//            }
//
//        }
//        return true;
//    }


    public void resetRent() {
        this.rent = new Rent();
    }


}
