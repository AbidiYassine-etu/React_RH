package com.example.React_back.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class athenticationDTO {
    private String email;

    private String password;

    private String fullName;

    public athenticationDTO(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

}
