package com.mehmetgenc.secondhand.user.dto;

import com.mehmetgenc.secondhand.user.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails from){
        return new UserDetailsDto(
                from.getPhoneNumber(),
                from.getAddress(),
                from.getCity(),
                from.getCountry(),
                from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> listFrom){
        return listFrom.stream().map(this::convert).collect(Collectors.toList());

    }

}
