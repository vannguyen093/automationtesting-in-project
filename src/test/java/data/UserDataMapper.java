package data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;

public class UserDataMapper {

    public static CustomerData[] getUserData(String jsonFilePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + jsonFilePath), CustomerData[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
