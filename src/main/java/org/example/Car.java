package org.example;

public class Car {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    public Car(String name){
        this.name = name;
    }
}
