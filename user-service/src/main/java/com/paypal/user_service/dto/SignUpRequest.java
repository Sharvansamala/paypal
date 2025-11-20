package com.paypal.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String adminKey;
}


//public record SignUpRequest(String name, String email, String password, String adminKey) {
//
//}
