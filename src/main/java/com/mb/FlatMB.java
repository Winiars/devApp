package com.mb;

import com.facade.FlatFacade;
import com.model.Flat;
import com.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.FacesConverter;
import java.util.Date;
import java.util.List;

/**
 * Created by Gruby on 20.04.2017.
 */
@ManagedBean
@ViewScoped
public class FlatMB extends AbstractMB {
    private FlatFacade flatFacade;
    private Flat flat;
    private Flat flatWithTenants;
    private String[] bookedDays;

    private static final String QUOTE = "'";
    private static final String TIME_FORMAT = "T00:00:00";
    private static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";

//    @ManagedProperty(value = "#{userMB}")
//    private UserMB userMB;

    private List<Flat> filteredFlats;

    public List<Flat> getFilteredFlats() {
        return filteredFlats;
    }

//    public void setUserMB(UserMB userMB) {
//        this.userMB = userMB;
//    }

    public void setFilteredFlats(List<Flat> filteredFlats) {
        this.filteredFlats = filteredFlats;
    }

    public FlatFacade getFlatFacade() {
        if (flatFacade == null) {
            flatFacade = new FlatFacade();
        }
        return flatFacade;
    }

    public Flat getFlat() {
        if (flat == null) {
            System.out.println("flat rowne null");
            this.flat = new Flat();
        }
        System.out.println("getFlat" + flat.getId());
        return flat;
    }

    public void setFlatFacade(FlatFacade flatFacade) {
        this.flatFacade = flatFacade;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public void createFlat() {
        try {
            getFlatFacade().createFlat(this.flat);
            resetFlat();
            displayInfoMessageToUser("Flat created with succes!");

        } catch (Exception e) {
            e.printStackTrace();
            displayInfoMessageToUser("Oops we couldn't create flat");
        }
    }

    public void deleteFlat() {
        try {
            getFlatFacade().deleteFlat(this.flat);
            resetFlat();
            displayInfoMessageToUser("Flat deleted with succes!");
        } catch (Exception e) {
            e.printStackTrace();
            displayInfoMessageToUser("Ops we couldn't delete flat");
        }

    }

    public void deleteFilteredFlats() {

        for (int i = 0; i < filteredFlats.size(); i++) {
            try {
                getFlatFacade().deleteFlat(filteredFlats.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                displayErrorMessageToUser("Ops we couldn't delete flat");
            }
        }

    }


    public void resetFlat() {
        this.flat = new Flat();
    }

    public List<Flat> getAllFlats() {
        return getFlatFacade().listOfAllFlats();
    }

    public void updateFlat() {
        try {
            getFlatFacade().updateFlat(this.flat);
            resetFlat();
            displayInfoMessageToUser("Flat updated with success!");
        } catch (Exception e) {
            e.printStackTrace();
            displayInfoMessageToUser("Oops we couldn't update flat");
        }
    }

    public void navigateWithFlash() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("flat", flat);

    }

    public void pullFlatFromFlash() {
        Flat flatToUpdate = (Flat) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flat");
        navigateWithFlash();
        this.flat = flatToUpdate;

    }


    public Date getToday() {
        return new Date();
    }

    public String navigateToReservationWithFlash() {
        navigateWithFlash();
        return "/pages/protected/user/flatReservation?faces-redirect=true";
    }

    public String navigateToUpdateWithFlash() {
        navigateWithFlash();
        return "flatsUpdate?faces-redirect=true";
    }

    public Flat getFlatWithTenants(){
        if(flatWithTenants==null){
            flatWithTenants=getFlatFacade().findFlatWithTenants(flat.getId());
        }return flatWithTenants;
    }


    public String getDisabledDays() {
        return "[\"5-15-2013\", \"6-23-2013\"]";
    }




}


