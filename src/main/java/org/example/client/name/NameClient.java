package org.example.client.name;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class NameClient {
    private final static String SERVICE_NAME = "/";
    private final String baseUrl;
    private final RestTemplate restTemplate;
    public NameClient(@Value("${base.url}") String baseUrl, RestTemplate restTemplate){
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public String shoutRandomLetter(){
        HttpEntity <String> req = new HttpEntity<>(null, getHeaders());
        System.out.println(baseUrl + SERVICE_NAME);
        String randomLetter = restTemplate.postForObject(baseUrl + SERVICE_NAME, req, String.class);
        return randomLetter.toUpperCase();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.setContentType(null);
        return headers;
    }
}
