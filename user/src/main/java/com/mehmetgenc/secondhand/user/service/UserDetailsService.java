package com.mehmetgenc.secondhand.user.service;

import com.mehmetgenc.secondhand.user.dto.CreateUserDetailsRequest;
import com.mehmetgenc.secondhand.user.dto.UpdateUserDetailsRequest;
import com.mehmetgenc.secondhand.user.dto.UserDetailsDto;
import com.mehmetgenc.secondhand.user.dto.UserDetailsDtoConverter;
import com.mehmetgenc.secondhand.user.exception.UserDetailsNotFoundException;
import com.mehmetgenc.secondhand.user.model.User;
import com.mehmetgenc.secondhand.user.model.UserDetails;
import com.mehmetgenc.secondhand.user.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsDtoConverter converter;
    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsDtoConverter converter) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.converter = converter;
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request){
        User user = userService.findUserById(request.getUserId());
        UserDetails userDetails = new UserDetails(
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                user
        );
        return converter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final Long userDetailsId, final UpdateUserDetailsRequest request){
        UserDetails userDetails = findUserDetailsById(userDetailsId);
        UserDetails updatedUserDetails = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                userDetails.getUser()
        );
        return converter.convert(userDetailsRepository.save(updatedUserDetails));
    }
    public void deleteUserDetails(final Long userDetailsId){
        findUserDetailsById(userDetailsId);
        userDetailsRepository.deleteById(userDetailsId );
    }


    private UserDetails findUserDetailsById(final Long userDetailsId){
       return userDetailsRepository.findById(userDetailsId)
                .orElseThrow(()-> new UserDetailsNotFoundException(
                        "User details couldn't be found by following id: " + userDetailsId));
    }
}
