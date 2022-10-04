package org.example;


import org.example.client.name.NameClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
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
        //ideally you want to use Mockito.eq for the string and HttpEntity to match any captured entities, then we would want to make sure the last arg matches the class we are returning
        when(restTemplate.postForObject(Mockito.any(String.class), Mockito.any(HttpEntity.class), Mockito.eq(String.class))).thenReturn(letterToBeRurned);
        String expected = nameClient.shoutRandomLetter();
        Mockito.verify(restTemplate, Mockito.times(1)).postForObject(Mockito.any(String.class), Mockito.any(HttpEntity.class), Mockito.eq(String.class));
        System.out.println(expected);
    }

    @Test
    public void testNameClientWithArgCaps(){
        String letterToBeRurned = "b";
        nameClient = new NameClient("baseUrl", restTemplate);
        when(restTemplate.postForObject(Mockito.any(String.class), Mockito.any(HttpEntity.class), Mockito.eq(String.class))).thenReturn(letterToBeRurned);
        String expected = nameClient.shoutRandomLetter();
        ArgumentCaptor <String> urlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor <HttpEntity> httpEntity = ArgumentCaptor.forClass(HttpEntity.class);
        ArgumentCaptor <Class> c = ArgumentCaptor.forClass(Class.class);
        Mockito.verify(restTemplate).postForObject(urlCaptor.capture(), httpEntity.capture(), c.capture());
        Assertions.assertTrue("baseUrl/".equals(urlCaptor.getValue()));
        Assertions.assertEquals(String.class, c.getValue());

    }

}
