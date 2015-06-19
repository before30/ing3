package me.joseph.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectMapperUtil {


    public static ObjectMapper generateObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    public static String convertObjectIntoJsonString(Object object) {
        String jsonBody = null;

        ObjectMapper objectMapper = generateObjectMapper();
        try{
            if(null != object)
                jsonBody = objectMapper.writeValueAsString(object);
        }catch (Exception e) {
            LogUtils.debugLog.debug("Error during convert object to json");
            LogUtils.debugLog.debug("convert error ", e);
        }

        return jsonBody;
    }
}