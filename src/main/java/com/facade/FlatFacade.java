package com.facade;

import com.dao.FlatDAO;
import com.dao.UserDAO;
import com.model.Flat;
import com.model.User;

import java.util.List;

/**
 * Created by Gruby on 20.04.2017.
 */
public class FlatFacade {

    private FlatDAO flatDAO=new FlatDAO();
    private UserDAO userDAO=new UserDAO();


    public void createFlat(Flat flat){
        flatDAO.beginTransaction();
        flatDAO.save(flat);
        flatDAO.commitTransaction();
        flatDAO.closeTransaction();
    }
    public List<Flat> listOfAllFlats(){
        flatDAO.beginTransaction();
        List<Flat> flats=flatDAO.findAll();
        flatDAO.closeTransaction();
        return flats;
    }

    public void deleteFlat (Flat flat){
        flatDAO.beginTransaction();
        Flat flatToDelete = flatDAO.findReference(flat.getId());
        flatDAO.delete(flatToDelete);
        flatDAO.commitTransaction();
        flatDAO.closeTransaction();
    }

    public void updateFlat (Flat flat){
        flatDAO.beginTransaction();
        Flat flatToUpdate=flatDAO.find(flat.getId());
        flatToUpdate.setName(flat.getName());
        flatToUpdate.setCity(flat.getCity());
        flatToUpdate.setDescription(flat.getDescription());
        flatToUpdate.setFinishDate(flat.getFinishDate());
        flatToUpdate.setStartDate(flat.getStartDate());
        flatToUpdate.setStreet(flat.getStreet());
        flatDAO.update(flatToUpdate);
        flatDAO.commitTransaction();
        flatDAO.closeTransaction();
    }



    public Flat findFlatWithTenants(int flatId){
        flatDAO.beginTransaction();
        Flat flat=flatDAO.findFlatWithTenants(flatId);
        flatDAO.closeTransaction();
        return flat;
    }


}
