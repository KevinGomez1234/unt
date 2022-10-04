package org.example;

import org.example.client.person.PersonClient;
import org.example.client.person.Person;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class PersonClientTest {
    @Mock
    RestTemplate template;

    @InjectMocks
    PersonClient client;

    String baseUrl;

    final String SERVICE_NAME = "/";

    AutoCloseable closeable;
    @BeforeEach
    public void initService(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void destroyService() throws Exception{
        closeable.close();
    }

    @Test
    public void clientTest(){
        client = new PersonClient(template, baseUrl);
        org.example.Person p = new RegularPerson("1", "This is returned", "dob", Collections.emptyList());

        when(template.postForObject(Mockito.any(String.class), Mockito.any(HttpEntity.class), Mockito.eq(org.example.Person.class))).thenReturn(p);
        org.example.Person g =  client.getPersonById("1234");
        System.out.println(g.getName());
    }
}
