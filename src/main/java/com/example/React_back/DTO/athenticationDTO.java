package com.example.React_back.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class athenticationDTO {
    private String token;
    private String role;

    public athenticationDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
