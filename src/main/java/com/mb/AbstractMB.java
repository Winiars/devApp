package com.mb;

import com.utils.CustomResourceBundle;
import com.utils.Messages;
import com.utils.ReadPropertiesFile;

/**
 * Created by Gruby on 11.04.2017.
 */
public class AbstractMB {

    protected void displayErrorMessageToUser(String message) {
        Messages messageUtil = new Messages();
        messageUtil.sendErrorMessageToUser(message);
    }

    protected void displayInfoMessageToUser(String message) {
        Messages messageUtil = new Messages();
        messageUtil.sendInfoMessageToUser(message);
    }

    protected void displayInfoMessageToUserAfterRedirect(String message) {
        Messages messagesUtil = new Messages();
        messagesUtil.sendInfoMessageToUserAfterReddirect(message);
    }

    protected String getPropertyErrorMessageValue(String key) {
        ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
        return readPropertiesFile.getPropertyValue(key);
    }

    protected String getPropertyMessageValueFromCustomMessageBundle(String key) {
        CustomResourceBundle labels = new CustomResourceBundle();
        return labels.getString(key);
    }

}
