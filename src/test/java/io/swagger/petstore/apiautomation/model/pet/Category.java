package io.swagger.petstore.apiautomation.model.pet;

public class Category {
private Long id;
private String  name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
