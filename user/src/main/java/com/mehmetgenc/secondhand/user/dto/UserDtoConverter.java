package com.mehmetgenc.secondhand.user.dto;

import com.mehmetgenc.secondhand.user.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    private final UserDetailsDtoConverter converter;

    public UserDtoConverter(UserDetailsDtoConverter converter) {
        this.converter = converter;
    }

    public UserDto convert(User from){
        return new UserDto(
                from.getMail(),
                from.getFirstName(),
                from.getMiddleName(),
                from.getLastName(),
                converter.convert(new ArrayList<>(from.getUserDetailsSet())));
    }

    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
