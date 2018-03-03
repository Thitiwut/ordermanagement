package classes.object;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class RequestBodyParser {

    public HashMap<String,Object> JSONBodyToMap(HttpServletRequest request){
        try {
            InputStream inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream , StandardCharsets.UTF_8));

            HashMap<String,Object> result = new ObjectMapper().readValue(reader, HashMap.class);
            return result;

        } catch (IOException e) {
            HashMap<String,Object> result = new HashMap<>();
            result.put("action", "unhandle");
            return result;
        }
    }
}
