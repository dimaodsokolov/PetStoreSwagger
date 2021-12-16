package utils;

import petstore.api.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
public class MarshalingExample {


    public static void main(String[] args) {

        String json = null;
        String jsonNiceLook = null;
        String jsonPath = "src/main/resources/user.json";
        ObjectMapper mapper = new ObjectMapper();
        User testUser1 = new User();

        try {
            testUser1 = mapper.readValue(new File(jsonPath), User.class);
        } catch (IOException e) {
            Log.LOG.error(e);
        }

        try {
            json = mapper.writeValueAsString(testUser1);
            jsonNiceLook = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser1);
        } catch (JsonProcessingException e) {
            Log.LOG.error(e);
        }


        System.out.println(json);
//        Log.LOG.info(json);
        System.out.println(testUser1.toString());
//        Log.LOG.info(testUser1.toString());
        System.out.println(jsonNiceLook);
//        Log.LOG.info(jsonNiceLook);
    }
}
