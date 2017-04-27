package com.validators;

import com.facade.UserFacade;
import com.mb.RegisterMB;
import com.utils.Messages;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Gruby on 13.04.2017.
 */

@FacesValidator("nickValidator")
public class NickValidator implements Validator {


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        UserFacade userFacade = new UserFacade();
        Messages messages = new Messages();

        if (userFacade.nickAlreadyExists(o.toString())) {
            FacesMessage msg =
                    new FacesMessage(" Nick validation failed.",
                            "Musisz wpisac inny");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }


    }
}
