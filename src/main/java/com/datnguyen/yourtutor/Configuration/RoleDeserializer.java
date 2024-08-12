package com.datnguyen.yourtutor.Configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.datnguyen.yourtutor.DTO.Role;

import java.io.IOException;

public class RoleDeserializer extends JsonDeserializer<Role> {
    @Override
    public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String role = p.getText().toUpperCase(); // Convert to uppercase
        return Role.valueOf(role); // Map to enum
    }
}
