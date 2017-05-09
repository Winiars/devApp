package com.mb;

import antlr.StringUtils;
import com.facade.FlatFacade;
import com.model.Flat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Created by Gruby on 20.04.2017.
 */
@ManagedBean
@ViewScoped
public class FlatMB extends AbstractMB {
    private FlatFacade flatFacade;
    private Flat flat;
    private Flat flatWithTenants;

    private List<Flat> filteredFlats;

    public List<Flat> getFilteredFlats() {
        return filteredFlats;
    }

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
            this.flat = new Flat();
        }
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

    public Flat getFlatWithTenants() {
        if (flatWithTenants == null) {
            flatWithTenants = getFlatFacade().findFlatWithTenants(flat.getId());
        }
        return flatWithTenants;
    }


}


