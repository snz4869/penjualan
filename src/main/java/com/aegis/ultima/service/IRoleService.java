package com.aegis.ultima.service;

import com.aegis.ultima.model.Role;

public interface IRoleService {
    Role findByName(String name);
}
