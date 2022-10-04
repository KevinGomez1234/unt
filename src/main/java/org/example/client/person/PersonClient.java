package org.example.client.person;

import org.example.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.example.RegularPerson;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Component
public class PersonClient {
    final static String SERVICE_NAME = "/";
    private RestTemplate restTemplate;
    private String baseUrl;
    public PersonClient(RestTemplate restTemplate, @Value("${base.url}")String baseUrl){
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Person getPersonById(String id){
        HttpHeaders headers = new HttpHeaders();
        System.out.println(baseUrl + " baseurl");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity <Person> httpEntity = new HttpEntity<>(null, headers);
        Person c = restTemplate.postForObject(baseUrl + SERVICE_NAME ,httpEntity, Person.class);
        return new RegularPerson(id, c.getName(), "dob", Collections.emptyList());
    }
}
