package com.example.ems.payload;

import com.example.ems.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotEmpty
    @Size(min = 3, message = "Username must be at least 3 characters!")
    private String name;
    @Email(message = "Email address is not valid!")
    @NotEmpty(message = "Email is required!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars!")
    private String password;
    private Role role;
    private UserDto manager;

    @JsonIgnore
    public String getPassword(){
        return this.password;
    }
}
