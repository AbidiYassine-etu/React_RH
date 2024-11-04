package com.example.React_back.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto <T>{
    private boolean isSuccess;
    private String message;
    private T response;
}
