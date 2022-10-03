package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.json.ReceivePersonRequest.PersonRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{

    public static void main( String[] args ) throws JsonProcessingException {
        SpringApplication.run(App.class, args);
    }

    public Integer returnOne(){
        return 1;
    }


}
