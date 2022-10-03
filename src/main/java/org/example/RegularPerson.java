package org.example;

import java.util.List;

public class RegularPerson implements Person{
    private final String name;
    private final String dob;
    private final String id;
    private List <Car> cars;
    public RegularPerson(String id, String name, String dob, List <Car> cars){
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.cars = cars;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDOB() {
        return this.dob;
    }

    @Override
    public List<Car> getCars() {
        return this.cars;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
