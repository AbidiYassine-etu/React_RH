package com.example.React_back.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true) // Enable method chaining for setters
public class ResponseDTO {
    private String token;
    private long expiresIn;

    public ResponseDTO() {
        // Default constructor, no initialization needed here
    }

    // Optional: Parameterized constructor if you want to set fields directly
    public ResponseDTO(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
