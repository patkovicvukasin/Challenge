package com.challenge.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentCreateRequest {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not valid.")
    private String email;

    @JsonProperty("isAdmin")
    @NotNull(message = "isAdmin is required.")
    private Boolean isAdmin;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @JsonProperty("isAdmin")
    public Boolean isAdmin() { return isAdmin; }

    @JsonProperty("isAdmin")
    public void setAdmin(Boolean admin) { isAdmin = admin; }
}
