package com.thespot.bookings.security.dao.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String mobileNumber;
    private String password;
}
