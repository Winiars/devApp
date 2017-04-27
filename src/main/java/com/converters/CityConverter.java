package com.converters;

import com.facade.CityFacade;
import com.model.City;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by Gruby on 20.04.2017.
 */
@ManagedBean
@RequestScoped
@FacesConverter (forClass = City.class)
public class CityConverter implements Converter {

    private CityFacade cityFacade= new CityFacade();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int cityId;
        try {
           cityId = Integer.parseInt(s);

        }catch (NumberFormatException e){
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"problem z konwersją stringa na int","gruby problem"));
        }
        return cityFacade.findCity(cityId);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if(o==null){
            return "";
        }
        City city =(City) o;
        return String.valueOf(city.getId());

    }
}
