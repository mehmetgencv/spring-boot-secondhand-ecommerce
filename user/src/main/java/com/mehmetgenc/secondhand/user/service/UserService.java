package com.mehmetgenc.secondhand.user.service;

import com.mehmetgenc.secondhand.user.dto.UserDto;
import com.mehmetgenc.secondhand.user.dto.UserDtoConverter;
import com.mehmetgenc.secondhand.user.exception.UserIsNotActiveException;
import com.mehmetgenc.secondhand.user.model.User;
import com.mehmetgenc.secondhand.user.dto.CreateUserRequest;
import com.mehmetgenc.secondhand.user.dto.UpdateUserRequest;
import com.mehmetgenc.secondhand.user.exception.UserNotFoundException;
import com.mehmetgenc.secondhand.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getMiddleName(),
                userRequest.getLastName(),
                true
        );
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        if (!user.getActive()){
            logger.warn(String.format("The user is not active! User Mail: %s", mail));
            throw new UserIsNotActiveException();
        }
        User updatedUser = new User(user.getId(),
                user.getMail(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getMiddleName(),
                updateUserRequest.getLastName());
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
    public void deactivateUser(Long id) {
        changeActivateUser(id, false);
    }

    public void activateUser(Long id) {
        changeActivateUser(id, true);
    }
    public void deleteUser(Long id) {
        if(doesUserExists(id)){
            userRepository.deleteById(id);
        }else{
            throw new UserNotFoundException("User couldn't be found by following id: " + id);
        }
    }

    private void changeActivateUser(Long id, Boolean isActive){
        User user = findUserById(id);
        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                isActive);
        userRepository.save(updatedUser);
    }

    private User findUserByMail(String mail){
        return userRepository.findUserByMail(mail)
                .orElseThrow(()-> new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    private User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User couldn't be found by following id: " + id));
    }

    private boolean doesUserExists(Long id){
        return userRepository.existsById(id);
    }


}
