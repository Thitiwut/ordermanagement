package test;

import classes.object.RequestBodyParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RequestBodyParserTest {

    @Test
    void JSONBodyToMap() throws IOException {
            HashMap<String,Object> expected = new HashMap<>();
            expected.put("action", "add_product");
            expected.put("product_name", "มังคุด");
            expected.put("product_type", "regular");
            expected.put("supplier_id", 1);
            String str = "{\"action\":\"add_product\",\"product_name\":\"มังคุด\",\"product_type\":\"regular\",\"supplier_id\":1}";
            HashMap<String,Object> result = new ObjectMapper().readValue(str, HashMap.class);

            assertEquals((Integer)expected.get("supplier_id"), (Integer)result.get("supplier_id"));
    }
}