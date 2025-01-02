package com.aegis.ultima.service;

import com.aegis.ultima.dto.UserRequestDTO;
import com.aegis.ultima.util.BaseClassDomain;

public interface IUserService {
    BaseClassDomain<UserRequestDTO> save(UserRequestDTO user);
    BaseClassDomain<UserRequestDTO> activateUser(UserRequestDTO user);

}
