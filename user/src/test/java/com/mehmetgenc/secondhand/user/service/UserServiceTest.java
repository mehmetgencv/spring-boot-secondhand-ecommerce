package com.mehmetgenc.secondhand.user.service;

import com.mehmetgenc.secondhand.user.TestSupport;
import com.mehmetgenc.secondhand.user.dto.CreateUserRequest;
import com.mehmetgenc.secondhand.user.dto.UpdateUserRequest;
import com.mehmetgenc.secondhand.user.dto.UserDto;
import com.mehmetgenc.secondhand.user.dto.UserDtoConverter;
import com.mehmetgenc.secondhand.user.exception.UserIsNotActiveException;
import com.mehmetgenc.secondhand.user.exception.UserNotFoundException;
import com.mehmetgenc.secondhand.user.model.User;
import com.mehmetgenc.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends TestSupport {
    private UserDtoConverter converter;
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp(){
        converter = mock(UserDtoConverter.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository, converter);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList(){
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        when(userRepository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();
        assertEquals(userDtoList, result);
        verify(userRepository).findAll();
        verify(converter).convert(userList);
    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto(){
        String mail = "info@mehmetgenc.net";
        User user = generateUser(mail);
        UserDto userDto = generateUserDto(mail);
        when(userRepository.findUserByMail(mail)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserByMail(mail);
        assertEquals(userDto, result);
        verify(userRepository).findUserByMail(mail);
        verify(converter).convert(user);
    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "info@mehmetgenc.net";
        when(userRepository.findUserByMail(mail)).thenReturn(Optional.empty());

       assertThrows(UserNotFoundException.class, ()->
                userService.getUserByMail(mail));
        verify(userRepository).findUserByMail(mail);
        verifyNoInteractions(converter);
    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto(){
        String mail = "info@mehmetgenc.net";
        CreateUserRequest createUserRequest = new CreateUserRequest(mail, "firstName", "", "lastName");

        User user = new User(mail, "firstName", "", "lastName", false);
        User savedUser = new User(1L, mail, "firstName", "", "lastName", false);
        UserDto userDto = new UserDto(mail, "firstName", "", "lastName");


        when(userRepository.save(user)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.createUser(createUserRequest);
        assertEquals(userDto, result);
        verify(userRepository).save(user);
        verify(converter).convert(savedUser);
    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto(){
        String mail = "info@mehmetgenc.net";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest( "firstName2", "", "lastName2");

        User user = new User(1L, mail, "firstName", "", "lastName", true);
        User updatedUser = new User(1L, mail, "firstName2", "", "lastName2", true);
        User savedUser = new User(1L, mail, "firstName2", "", "lastName2", true);
        UserDto userDto = new UserDto(mail, "firstName2", "", "lastName2");


        when(userRepository.findUserByMail(mail)).thenReturn(Optional.of(user));
        when(userRepository.save(updatedUser)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser(mail, updateUserRequest);
        assertEquals(userDto, result);

        verify(userRepository).findUserByMail(mail);
        verify(userRepository).save(updatedUser);
        verify(converter).convert(savedUser);
    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "info@mehmetgenc.net";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest( "firstName2", "", "lastName2");

        when(userRepository.findUserByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()-> userService.updateUser(mail, updateUserRequest));


        verify(userRepository).findUserByMail(mail);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException(){
        String mail = "info@mehmetgenc.net";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest( "firstName2", "", "lastName2");
        User user = new User(1L, mail, "firstName", "", "lastName", false);
        when(userRepository.findUserByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, ()-> userService.updateUser(mail, updateUserRequest)); ;


        verify(userRepository).findUserByMail(mail);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveFalse(){
        String mail = "info@mehmetgenc.net";
        User user = new User(userId, mail, "firstName", "", "lastName", true);
        User savedUser = new User(userId, mail, "firstName", "", "lastName", false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).save(savedUser);
    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()-> userService.deactivateUser(userId));

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue(){
        String mail = "info@mehmetgenc.net";
        User user = new User(userId, mail, "firstName", "", "lastName", false);
        User savedUser = new User(userId, mail, "firstName", "", "lastName", true);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.activateUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).save(savedUser);
    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()-> userService.activateUser(userId));

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteUser(){
        String mail = "info@mehmetgenc.net";
        User user = new User(userId, mail, "firstName", "", "lastName", false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()-> userService.deleteUser(userId));

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

}