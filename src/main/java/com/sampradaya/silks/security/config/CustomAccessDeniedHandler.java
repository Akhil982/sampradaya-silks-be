package com.thespot.bookings.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thespot.bookings.api.bean.ResponseBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseBean<String> resBean = new ResponseBean<>(HttpStatus.FORBIDDEN.value(),
                "Authorization Failed", "You are not authorized to access this resource.");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, resBean);
        responseStream.flush();
    }
}
