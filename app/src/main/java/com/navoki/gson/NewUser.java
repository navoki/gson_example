package com.navoki.gson;

import com.google.gson.annotations.JsonAdapter;

public class NewUser {
    public String name;
    public String role;
    public String phone;
    public Address address;
    @JsonAdapter(GenderDataConverter.class)
    public Gender gender; // Enum MALE, FEMALE
}

