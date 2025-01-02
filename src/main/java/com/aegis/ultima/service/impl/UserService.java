package com.aegis.ultima.service.impl;

import com.aegis.ultima.model.User;
import com.aegis.ultima.dto.UserRequestDTO;
import com.aegis.ultima.repository.UserRepository;
import com.aegis.ultima.service.IUserService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
import com.aegis.ultima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userService")
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private CommonFunction commonFunction;

    String loginUsername = commonFunction.getLoggedInUsername();

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
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
    public BaseClassDomain<UserRequestDTO> save(UserRequestDTO user) {
        BaseClassDomain<UserRequestDTO> returnValue = new BaseClassDomain<UserRequestDTO>();
        try {
            User nUser = new User();
            nUser = userRepository.findUserByUsername(user.getUsername());

            if (nUser != null){
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("Username Already exist");
            } else {
                nUser = user.getUserFromDto();
                nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                nUser.setRole("KASIR");
                nUser.setIsActive(false);
                nUser.setCreatedAt(DateUtils.getCurrentDate());
                nUser.setCreatedBy(loginUsername);

                userRepository.save(nUser);
                returnValue.setResponseSucceed(user);
            }

        } catch (Exception e){
            returnValue.setResponseException();
        }
        return returnValue;
    }

    @Override
    public BaseClassDomain<UserRequestDTO> activateUser(UserRequestDTO user) {
        BaseClassDomain<UserRequestDTO> returnValue = new BaseClassDomain<UserRequestDTO>();

        try {
            User nUser = userRepository.findUserByUsername(user.getUsername());
            if (nUser != null){
                nUser.setIsActive(true);
                nUser.setUpdatedAt(DateUtils.getCurrentDate());
                nUser.setUpdatedBy(loginUsername);
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
