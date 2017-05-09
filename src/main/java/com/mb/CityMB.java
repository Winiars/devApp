package com.mb;

import com.facade.CityFacade;
import com.model.City;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Gruby on 19.04.2017.
 */
@ManagedBean
@ViewScoped
public class CityMB extends AbstractMB {

    private CityFacade cityFacade;
    private City city;


    public CityFacade getCityFacade() {
        if (cityFacade == null) {
            cityFacade = new CityFacade();
        }
        return cityFacade;
    }

    public City getCity() {
        if (city == null) {
            city = new City();
        }
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public void createCity() {
        try {

            getCityFacade().createCity(this.city);
            resetCity();
            displayInfoMessageToUser("City created with succes!");
        } catch (Exception e) {
            displayInfoMessageToUser("Ops we couldn't create city");
        }

    }

    public List<City> getAllCities() {

        return getCityFacade().listOfAll();
    }

    public void deleteCity() {
        try {
            getCityFacade().deleteCity(city);
            resetCity();
            displayInfoMessageToUser("City deleted with succes!");
        } catch (Exception e) {
            displayInfoMessageToUser("Ops we couldn't delete city");
        }

    }

    public void resetCity() {
        this.city = new City();
    }

}
