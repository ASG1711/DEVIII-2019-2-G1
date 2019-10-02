package dev3.dev3project.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    
   @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional
                .ofNullable(Date.valueOf(localDate))
                .orElse(null);
    }
    
   @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Optional
                .ofNullable(date.toLocalDate())
                .orElse(null);
    }   
}