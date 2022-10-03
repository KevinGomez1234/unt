package org.example;

import org.example.client.person.PersonClient;
import org.example.json.ReceivePersonRequest.PersonRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceivePersonController {
    private final PersonClient personClient;
    public ReceivePersonController(PersonClient p){
        this.personClient = p;
    }

    @PostMapping("/get-employee")
    public String getEmployee(@RequestBody PersonRequest request){
        if(request.getId() == null){
            return "Empty Id";
        }
        Person p = personClient.getPersonById(request.getId());
        return request.getId() + " " + p.getName();
    }
}
