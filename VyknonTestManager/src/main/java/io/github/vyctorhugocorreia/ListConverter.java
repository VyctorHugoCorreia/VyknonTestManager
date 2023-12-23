package io.github.vyctorhugocorreia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.io.IOException;
import java.util.List;

@Convert
public class ListConverter implements AttributeConverter<List<?>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<?> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle the exception appropriately
            return null;
        }
    }

    @Override
    public List<?> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(dbData, List.class);
        } catch (IOException e) {
            // Handle the exception appropriately
            return null;
        }
    }
}
