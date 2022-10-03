package org.example.client.person;

import org.example.Person;
import org.springframework.stereotype.Component;
import org.example.RegularPerson;

import java.util.Collections;

@Component
public class PersonClient {
    public PersonClient(){

    }

    public Person getPersonById(String id){
        return new RegularPerson(id, "Kevin", "dob", Collections.emptyList());
    }
}
