package org.example.model;

public class ClientModel {
    private Integer id;
    private  String name;

    public ClientModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
