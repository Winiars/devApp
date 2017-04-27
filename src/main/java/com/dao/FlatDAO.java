package com.dao;

import com.model.Flat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gruby on 20.04.2017.
 */
public class FlatDAO extends GenericDAO<Flat> {

    public FlatDAO() {
        super(Flat.class);
    }

    public void delete(Flat flat){
        super.delete(flat.getId());
    }

    public Flat findFlatWithTenants(int flatId){
        Map<String,Object> parameters=new HashMap<String,Object>();
        parameters.put("flatId",flatId);
        return findOneResult(Flat.FIND_FLAT_BY_ID_WITH_TENANTS,parameters);
    }
}
