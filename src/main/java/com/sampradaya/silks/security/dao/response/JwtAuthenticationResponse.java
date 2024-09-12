package com.sampradaya.silks.security.dao.response;

import com.sampradaya.silks.security.entity.Role;
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
