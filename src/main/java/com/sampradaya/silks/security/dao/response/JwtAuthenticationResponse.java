package com.thespot.bookings.security.dao.response;

import com.thespot.bookings.security.entity.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String userId;
    private String email;
    private String token;
    private String firstName;
    private String lastName;
    private Role role;
}
