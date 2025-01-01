package com.aegis.ultima.service.impl;

import com.aegis.ultima.model.Product;
import com.aegis.ultima.model.Role;
import com.aegis.ultima.model.User;
import com.aegis.ultima.model.UserDto;
import com.aegis.ultima.repository.UserRepository;
import com.aegis.ultima.service.IRoleService;
import com.aegis.ultima.service.IUserService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    // Load user by username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        }
        return authorities;
    }

    @Override
    public BaseClassDomain<UserDto> save(UserDto user) {
        BaseClassDomain<UserDto> returnValue = new BaseClassDomain<UserDto>();
        try {
            User nUser = new User();
            nUser = userRepository.findByUsername(user.getUsername());

            if (nUser.getId() != null){
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("Username Already exist");
            } else {
                nUser = user.getUserFromDto();
                nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                nUser.setIsActive(user.getRole().equals("ADMIN"));
                nUser.setCreatedAt(DateUtils.getCurrentDate());
                nUser.setCreatedBy("admin");

                userRepository.save(nUser);
                returnValue.setResponseSucceed(user);
            }

        } catch (Exception e){
            returnValue.setResponseException();
        }
        return returnValue;
    }

    @Override
    public BaseClassDomain<UserDto> activateUser(UserDto user) {
        BaseClassDomain<UserDto> returnValue = new BaseClassDomain<UserDto>();

        try {
            User nUser = userRepository.findByUsername(user.getUsername());
            if (nUser.getId() != null){
                nUser.setIsActive(true);
                userRepository.save(nUser);
                returnValue.setResponseSucceed(user);
            } else {
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("User not exist");
            }
        } catch (Exception e) {
            returnValue.setResponseException();
        }
        return returnValue;
    }

}
