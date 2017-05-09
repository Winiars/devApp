package com.dao;

import com.model.City;

/**
 * Created by Gruby on 19.04.2017.
 */
public class CityDAO extends GenericDAO<City> {

    public CityDAO() {
        super(City.class);
    }

    public void delete(City city) {
        super.delete(city.getId());
    }


}
