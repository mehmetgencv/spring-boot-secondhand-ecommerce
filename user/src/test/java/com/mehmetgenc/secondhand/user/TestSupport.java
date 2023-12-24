package com.mehmetgenc.secondhand.user;

import com.mehmetgenc.secondhand.user.dto.UserDto;
import com.mehmetgenc.secondhand.user.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static final Long userId = 100L;
    public static List<User> generateUsers(){
        return IntStream.range(0, 5)
                .mapToObj(i->
                     new User(
                            (long) i,
                            i + "@mehmetgenc.net",
                            "firstName_" + i,
                            "",
                            "lastName_" + i,
                            new Random(2).nextBoolean())
                ).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList){
        return userList.stream().map(from-> new UserDto(
                from.getMail(),
                from.getFirstName(),
                from.getMiddleName(),
                from.getLastName())).toList();
    }

    public static User generateUser(String mail){
        return new User(
                userId,
                mail,
                "firstName_" + userId,
                "",
                "lastName_" + userId,
                true);
    }
    public static UserDto generateUserDto(String mail){
        return new UserDto(mail,
                "firstName_" + userId,
                "",
                "lastName_" + userId);
    }
}
