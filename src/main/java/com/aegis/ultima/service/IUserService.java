package com.aegis.ultima.service;

import com.aegis.ultima.model.User;
import com.aegis.ultima.model.UserDto;
import com.aegis.ultima.util.BaseClassDomain;

import java.util.List;

public interface IUserService {
    BaseClassDomain<UserDto> save(UserDto user);
    BaseClassDomain<UserDto> activateUser(UserDto user);

}
