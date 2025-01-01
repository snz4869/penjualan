package com.aegis.ultima.service.impl;

import com.aegis.ultima.model.Role;
import com.aegis.ultima.repository.RoleRepository;
import com.aegis.ultima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
