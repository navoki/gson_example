package com.navoki.gson;

import com.google.gson.annotations.Expose;

import java.util.List;

public class User {

    @Expose(serialize = true)
    public String name;
    public String role;
    public String phone;
    public String address;
    public List<String> projects;
}

