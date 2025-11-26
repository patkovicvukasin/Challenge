package com.challenge.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;

    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public StudentResponse() {}

    public StudentResponse(Long id, String name, String email, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @JsonProperty("isAdmin")
    public boolean isAdmin() { return isAdmin; }

    @JsonProperty("isAdmin")
    public void setAdmin(boolean admin) { isAdmin = admin; }
}
