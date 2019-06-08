package com.scumm.api.contracts;

import javax.validation.constraints.NotEmpty;

public class CategoryContract {


    private String id;

    @NotEmpty(message = "first_name can not be empty")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
