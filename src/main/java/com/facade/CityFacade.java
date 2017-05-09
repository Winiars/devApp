package com.facade;

import com.dao.CityDAO;
import com.model.City;
import com.model.User;

import java.util.List;

/**
 * Created by Gruby on 19.04.2017.
 */
public class CityFacade {

    private CityDAO cityDAO = new CityDAO();

    public void createCity(City city) {
        cityDAO.beginTransaction();
        cityDAO.save(city);
        cityDAO.commitTransaction();
        cityDAO.closeTransaction();

    }

    public List<City> listOfAll() {
        cityDAO.beginTransaction();
        List<City> listOfCities = cityDAO.findAll();
        cityDAO.closeTransaction();
        return listOfCities;
    }

    public void deleteCity(City city) {
        cityDAO.beginTransaction();
        City cityToDelete = cityDAO.findReference(city.getId());
        cityDAO.delete(cityToDelete);
        cityDAO.commitTransaction();
        cityDAO.closeTransaction();
    }

    public City findCity(int cityId) {
        cityDAO.beginTransaction();
        City city = cityDAO.find(cityId);
        cityDAO.closeTransaction();
        return city;

    }


}
