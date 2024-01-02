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
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(final CreateUserRequest userRequest) {
        User user = new User(
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getMiddleName(),
                userRequest.getLastName(),
                false
        );
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(final String mail, final UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        if (!user.getActive()){
            logger.warn(String.format("The user is not active! User Mail: %s", mail));
            throw new UserIsNotActiveException();
        }
        User updatedUser = new User(user.getId(),
                user.getMail(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getMiddleName(),
                updateUserRequest.getLastName(),
                user.getActive());
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
    public void deactivateUser(final Long id) {
        changeActivateUser(id, false);
    }

    public void activateUser(final Long id) {
        changeActivateUser(id, true);
    }
    public void deleteUser(final Long id) {
        findUserById(id);
        userRepository.deleteById(id);

    }

    private void changeActivateUser(final Long id, final Boolean isActive){
        User user = findUserById(id);
        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                isActive);
        userRepository.save(updatedUser);
    }

    private User findUserByMail(final String mail){
        return userRepository.findUserByMail(mail)
                .orElseThrow(()-> new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    protected User findUserById(final Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User couldn't be found by following id: " + id));
    }

    public Boolean isUserIdExist(Long id) {
        return userRepository.existsById(id);
    }
}
