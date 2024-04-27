package kr.freesol.cafeconnect.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.freesol.cafeconnect.security.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class RoleConverter implements AttributeConverter<Set<Role>, String> {
    private static final String SEPERATOR =",";

    @Override
    public String convertToDatabaseColumn(Set<Role> entitySetRole) {
        return entitySetRole.stream()
                .map(Enum::toString)
                .collect(Collectors.joining(SEPERATOR));
    }

    @Override
    public Set<Role> convertToEntityAttribute(String dbStringRole) {
        return Arrays.stream(dbStringRole.split(SEPERATOR))
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
