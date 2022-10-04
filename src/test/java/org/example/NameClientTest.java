package org.example;


import org.example.client.name.NameClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class NameClientTest {
    AutoCloseable closeable;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    NameClient nameClient;

    String baseUrl;
    @BeforeEach
    public void before(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() throws Exception{
        closeable.close();
    }
    @Test
    public void testNameClient(){
        String letterToBeRurned = "b";
        nameClient = new NameClient("baseUrl", restTemplate);
        //Any is used for Class types matching not for specific values, use eq for specific values.
        when(restTemplate.postForObject(Mockito.any(String.class), Mockito.any(HttpEntity.class), Mockito.eq(String.class))).thenReturn(letterToBeRurned);
        String expected = nameClient.shoutRandomLetter();
        System.out.println(expected);
    }

}
